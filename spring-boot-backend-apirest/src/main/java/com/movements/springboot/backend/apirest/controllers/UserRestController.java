package com.movements.springboot.backend.apirest.controllers;

import java.util.Date;
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
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movements.springboot.backend.apirest.editors.LowerCaseEditor;
import com.movements.springboot.backend.apirest.editors.RolesEditor;
import com.movements.springboot.backend.apirest.models.entity.Role;
import com.movements.springboot.backend.apirest.models.entity.AppUser;
import com.movements.springboot.backend.apirest.models.entity.Company;
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



	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, "roles", rolesEditor);

		binder.registerCustomEditor(String.class, "username", new LowerCaseEditor());

	}

	@GetMapping("/users")
	public List<AppUser> index() {
		return userService.findAll();
	}

	
	@GetMapping("/users/page/{page}")
	public Page<AppUser> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return userService.findAll(pageable);
	}
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/users/roles")
	public List<Role> rolesList() {
		return this.userService.findAllRoles();
	}
	
	
	//@Secured({"ROLE_USER", "ROLE_ADMIN"})
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

			newUser =userService.save(user);

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
			currentUser.setPassword(user.getPassword());
			currentUser.setRoles(user.getRoles());

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
	
	
	
	
	
	
	
	//@GetMapping(value = "/user/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		AppUser user = userService.findById(id);

		if (user == null) {
			flash.addFlashAttribute("error", "L'usuari no existeix a la BdD");
			return "redirect:/user/list";
		}
		model.put("user", user);
		model.put("title", "Usuari: " + user.getUsername());
		return "/user/view";
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("title", "Llistat d'usuaris");
		model.addAttribute("users", userService.findAll());
		return "/user/list";
	}

	@GetMapping(value = "/user/form")
	public String create(Map<String, Object> model) {

		AppUser user = new AppUser();

		model.put("user", user);
		model.put("title", "Formulari d'Usuari");

		return "/user/form";
	}

	@RequestMapping(value = "/user/form/{id}")
	public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		AppUser user = null;

		if (id != null) {
			user = userService.findById(id);
			if (user == null) {
				flash.addFlashAttribute("error", "L'identificador de l'usuari no existeix a la BdD");
				return "redirect:/user/list";
			}
		} else {
			flash.addFlashAttribute("error", "L'identificador de l'usuari no pot ser zero");
			return "redirect:/user/list";
		}

		model.put("user", user);
		model.put("title", "Formulari d'Usuari");

		return "/user/form";
	}

	@RequestMapping(value = "/user/form", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("user") AppUser user, BindingResult result, Model model,
			// @RequestParam(name = "roles[0].role.id", required = false) Long role1,
			// @RequestParam(name = "roles[1].role.id", required = false) Long role2,
			// @RequestParam(value = "roles", required = false) Long[] rolesId,
			RedirectAttributes flash, SessionStatus status) {

		// System.out.println("hola " + roleId.length);

		/*
		 * if (role1 == null & role2 == null) { user.setRoles(null);
		 * result.rejectValue("roles", "error.user", "Indicar com a mínim un rol");
		 * System.out.println("holnjhh "); } else { if (role1 != null) { Role role =
		 * userService.findRoleById(role1); System.out.println("aquest role : " +
		 * role.getRole()); // UserRolePK userRolePK = new UserRolePK(user, role); //
		 * UserRole userRole = new UserRole(userRolePK);
		 * 
		 * user.addRoles(role);
		 * 
		 * } if (role2 != null) { Role role = userService.findRoleById(role2);
		 * System.out.println("aquest role : " + role.getRole()); // UserRolePK
		 * userRolePK = new UserRolePK(user, role); // UserRole userRole = new
		 * UserRole(userRolePK);
		 * 
		 * user.addRoles(role);
		 * 
		 * } }
		 */
		/*
		 * for (int i = 0; i < roleId.length; i++) { Role role =
		 * userService.findRoleById(roleId[i]); System.out.println("aquest role : " +
		 * role.getRole()); UserRolePK userRolePK = new UserRolePK(user, role); UserRole
		 * userRole = new UserRole(userRolePK);
		 * 
		 * user.addRoles(userRole);
		 * 
		 * for (UserRole ro : user.getRoles()) { System.out.println("rol: " +
		 * ro.getDescription()); } System.out.println(user.getRoles()); }
		 */

		/*
		 * if (rolesId == null || rolesId.length == 0) { user.setRoles(null);
		 * result.rejectValue("roles", "error.user", "Indicar com a mínim un rol");
		 * System.out.println("holnjhh "); } else { for (int i = 0; i < rolesId.length;
		 * i++) { Role role = userService.findRoleById(rolesId[i]);
		 * System.out.println("aquest role : " + role.getRole()); user.addRoles(role);
		 * 
		 * for (UserRole ro : user.getRoles()) { System.out.println("rols num: " +
		 * rolesId.length + " rols a usuari: " + user.getRoles().size() + "usuari: " +
		 * user.getUsername() + " rol: " + ro.getDescription()); }
		 * System.out.println(user.getRoles()); } }
		 */

		if (result.hasErrors()) {
			System.out.println("errors");
			System.out.println(result.getAllErrors());
			model.addAttribute("title", "Formulari d'Usuari");
			return "/user/form";
		}

		String flashMessage = (user.getId() != null) ? "Usuari modificat correctament" : "Usuari creat correctament";

		userService.save(user);

		status.setComplete();
		flash.addFlashAttribute("success", flashMessage);

		/*
		 * for (UserRole ro : user.getRoles()) { System.out.println("rol: " +
		 * ro.getDescription() + " usuari: " + ro.getUser().getUsername()); }
		 * System.out.println(user.getRoles());
		 */

		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id != null) {
			userService.delete(id);
			flash.addFlashAttribute("success", "Usuari eliminat correctament");
		}
		return "redirect:/user/list";

	}

}
