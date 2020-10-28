package com.movements.springboot.backend.apirest.controllers;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.movements.springboot.backend.apirest.editors.PascalCaseEditor;
import com.movements.springboot.backend.apirest.editors.UpperCaseEditor;
import com.movements.springboot.backend.apirest.models.entity.Company;
import com.movements.springboot.backend.apirest.models.entity.Employee;
import com.movements.springboot.backend.apirest.models.entity.Task;
import com.movements.springboot.backend.apirest.models.enums.NifTypes;
import com.movements.springboot.backend.apirest.models.services.ICompanyService;
import com.movements.springboot.backend.apirest.models.services.IEmployeeService;
import com.movements.springboot.backend.apirest.validation.EmployeeValidator;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private EmployeeValidator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.addValidators(validator);

		binder.registerCustomEditor(String.class, "name", new PascalCaseEditor());
		binder.registerCustomEditor(String.class, "nif", new UpperCaseEditor());
		binder.registerCustomEditor(String.class, "email", new LowerCaseEditor());

	}

	@ModelAttribute("gender")
	public List<String> gender(){
		return Arrays.asList("Home","Dona");
	}
	
	@GetMapping("/employees")
	public List<Employee> index() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/page/{page}")
	public Page<Employee> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return employeeService.findAll(pageable);
	}
	
	
	//@Secured("ROLE_USER")
	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Employee employee = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			employee = employeeService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("message", "Error al realitzar la consulta a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
				

		if (employee == null) {
			response.put("message", "El treballador ID: ".concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			employeeService.delete(id);

		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar el treballador de la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "El treballador s'ha eliminat amb èxit");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Employee employee, BindingResult result) {

		Employee newEmployee = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err-> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			newEmployee = employeeService.save(employee);
			
		} catch (DataAccessException e) {
			
			response.put("message", "Error al realitzar la inserció a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}		

		response.put("message", "El treballador s'ha creat amb èxit");
		response.put("employee", newEmployee);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Employee employee, 
			BindingResult result, @PathVariable Long id){
		
		Employee currentEmployee = employeeService.findById(id);
		Employee updatedEmployee = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (employee == null ) {
			response.put("message", "Error: no s'ha pogut editar, el treballador ID: "
					.concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			currentEmployee.setName(employee.getName());
			currentEmployee.setBirthDate(employee.getBirthDate());
			currentEmployee.setComment(employee.getComment());
			currentEmployee.setCompany(employee.getCompany());
			currentEmployee.setEmail(employee.getEmail());
			currentEmployee.setGender(employee.getGender());
			currentEmployee.setIsEnabled(employee.getIsEnabled());
			currentEmployee.setNaf(employee.getNaf());
			currentEmployee.setNif(employee.getNif());
			currentEmployee.setNifType(employee.getNifType());
			
			updatedEmployee = employeeService.save(currentEmployee);
			
			
		} catch (DataAccessException e) {
			response.put("message", "Error al actualitzar el treballador a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El treballador s'ha actualitzat amb èxit");
		response.put("employee", updatedEmployee);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	

	
}
