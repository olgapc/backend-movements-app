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
import org.springframework.ui.Model;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.movements.springboot.backend.apirest.editors.PascalCaseEditor;
import com.movements.springboot.backend.apirest.models.entity.Information;
import com.movements.springboot.backend.apirest.models.entity.Task;
import com.movements.springboot.backend.apirest.models.services.ITaskService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class TaskRestController {

	//@Autowired
	//private ICompanyService companyService;

	//@Autowired
	//private IEmployeeService employeeService;

	@Autowired
	private ITaskService taskService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// If binder.registerCustomEditor(String.class,new UpperCaseEditor()); all the
		// String attributes to UpperCase
		binder.registerCustomEditor(String.class, "description", new PascalCaseEditor());
		binder.registerCustomEditor(String.class, "nameTemplate", new PascalCaseEditor());

	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/tasks/{idTask}")
	@ResponseStatus(HttpStatus.OK)
	public Task show(@PathVariable(value = "idTask") Long idTask, Model model, RedirectAttributes flash) {

		return taskService.findTaskById(idTask);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			
			taskService.delete(id);

		} catch (DataAccessException e) {
			
			response.put("message", "Error al eliminar la tasca de la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		response.put("message", "L'empresa s'ha eliminat amb èxit");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/tasks/upload-informations/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Information> uploadInformations(@PathVariable String term) {
		return taskService.findInformationByDescription(term);
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/tasks")
	public List<Task> index() {
		return taskService.findAll();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/tasks/page/{page}")
	public Page<Task> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return taskService.findAll(pageable);
	}
	

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Task task, BindingResult result) {

		System.out.println(task.getDeadline());
		Task newTask = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			
			newTask = taskService.save(task);

		} catch (DataAccessException e) {

			response.put("message", "Error al realitzar la inserció a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "La tasca s'ha creat amb èxit");
		response.put("task", newTask);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PutMapping("/tasks/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Task task, 
			BindingResult result, @PathVariable Long id) {

		Task currentTask = taskService.findTaskById(id);
		Task updatedTask = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (task == null) {
			response.put("message", "Error: no s'ha pogut editar, l'empresa ID: "
					.concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentTask.setDescription(task.getDescription());
			currentTask.setIsOptionalSubtask(task.isOptionalSubtask());
			currentTask.setIsToSend(task.getIsToSend());
			currentTask.setIsTemplate(task.getIsTemplate());
			currentTask.setTemplateName(task.getTemplateName());
			currentTask.setNumberToCalculateDeadlineToAlarm(task.getNumberToCalculateDeadlineToAlarm());
			currentTask.setTypeCalculationDeadline(task.getTypeCalculationDeadline());
			currentTask.setDeadline(task.getDeadline());
			currentTask.setEmployee(task.getEmployee());
			currentTask.setCompany(task.getCompany());
			
			
			currentTask.setIsDone(task.getIsDone());
			currentTask.setMainTask(task.getMainTask());
			currentTask.setIsMainTask(task.getIsMainTask());



			updatedTask = taskService.save(currentTask);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualitzar la tasca a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "La tasca s'ha actualitzat amb èxit");
		response.put("company", updatedTask);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/informations/page/{page}")
	public Page<Information> informationList(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return taskService.findAllInformations(pageable);
	}
	

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/informations/{idInformation}")
	@ResponseStatus(HttpStatus.OK)
	public Information showInformation(@PathVariable(value = "idInformation") Long idInformation, Model model, RedirectAttributes flash) {

		return taskService.findInformationById(idInformation);
	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/informations")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createInformation (@Valid @RequestBody Information information, BindingResult result) {

		Information newInformation = null;
		
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

			newInformation = taskService.saveInformation(information);

		} catch (DataAccessException e) {
			
			response.put("message", "Error al realitzar la inserció a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}

		response.put("message", "La informació s'ha creat amb èxit");
		response.put("information", newInformation);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PutMapping("/informations/{id}")
	public ResponseEntity<?> updateInformation(@Valid @RequestBody Information information, 
			BindingResult result, @PathVariable Long id) {

		Information currentInformation = taskService.findInformationById(id);
		Information updatedInformation = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (information == null) {
			response.put("message", "Error: no s'ha pogut editar, la informació ID: "
					.concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentInformation.setDescription(information.getDescription());
			currentInformation.setComment(information.getComment());


			updatedInformation = taskService.saveInformation(currentInformation);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualitzar la informació a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "La informació s'ha actualitzat amb èxit");
		response.put("information", updatedInformation);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/informations/{id}")
	public ResponseEntity<?> deleteInformation(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		
		try {
			
			taskService.deleteInformation(id);

		} catch (DataAccessException e) {

			response.put("message", "Error al esborrar informació!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "La informació s'ha esborrat amb èxit");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		

	}
	
}
	
	
	
	
	
	