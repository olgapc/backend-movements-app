package com.movements.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.movements.springboot.backend.apirest.editors.LowerCaseEditor;
import com.movements.springboot.backend.apirest.editors.RolesEditor;
import com.movements.springboot.backend.apirest.models.entity.Role;
import com.movements.springboot.backend.apirest.models.entity.AppUser;
import com.movements.springboot.backend.apirest.models.services.IUserService;

//@Secured("ROLE_ADMIN")
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private IUserService userService;

	@Autowired
	private RolesEditor rolesEditor;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, "roles", rolesEditor);

		binder.registerCustomEditor(String.class, "username", new LowerCaseEditor());

	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/users")
	public List<AppUser> index() {
		return userService.findAll();
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/users/page/{page}")
	public Page<AppUser> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return userService.findAll(pageable);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/roles")
	public List<Role> rolesList() {
		return userService.findAllRoles();
	}
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/users/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		AppUser user = null;
		Map<String, Object> response = new HashMap<>();

		try {
			user = userService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al realitzar la consulta a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (user == null) {
			response.put("message", "L'usuari ID: ".concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AppUser>(user, HttpStatus.OK);

	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody AppUser user, BindingResult result) {

		AppUser newUser = null;
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			String passwordBcrypt = passwordEncoder.encode(user.getPassword());
			user.setPassword(passwordBcrypt);
			newUser = userService.save(user);

		} catch (DataAccessException e) {
			
			response.put("message", "Error al realitzar l'inserció a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}

		response.put("message", "L'usuari s'ha creat amb èxit");
		response.put("user", newUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
	@Secured("ROLE_ADMIN")
	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody AppUser user, BindingResult result, @PathVariable Long id) {

		AppUser currentUser = userService.findById(id);
		AppUser updatedUser = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (user == null) {
			response.put("message", "Error: no s'ha pogut editar, l'usuari ID: "
					.concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentUser.setName(user.getName());
			currentUser.setLastName(user.getLastName());
			currentUser.setEmail(user.getEmail());
			//currentUser.setPassword(user.getPassword());
			currentUser.setUserRoles(user.getUserRoles());
			currentUser.setIsEnabled(user.getIsEnabled());
			//currentUser.set.setRoles(user.getRoles());
			currentUser.setComment(user.getComment());

			updatedUser = userService.save(currentUser);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualitzar l'usuari a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "L'usuari s'ha actualitzat amb èxit");
		response.put("user", updatedUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			userService.delete(id);

		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar l'usuari de la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "L'usuari s'ha eliminat amb èxit");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
}