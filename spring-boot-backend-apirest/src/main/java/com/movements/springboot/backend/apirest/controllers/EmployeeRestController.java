package com.movements.springboot.backend.apirest.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	//@Secured("ROLE_USER")
	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Employee employee = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			employee = employeeService.fetchByIdWithTasksWithCompany(id);
			
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

	//@Secured("ROLE_ADMIN")
	@GetMapping("/form/{companyId}")
	public String create(@PathVariable(value = "companyId", required = false) Long companyId, Map<String, Object> model,
			RedirectAttributes flash) {

		if (companyId == null) {
			return "redirect:/company/list";
		}
		Company company = companyService.findById(companyId);

		if (company == null) {
			flash.addFlashAttribute("error", "L\'empresa no existeix a la BdD");
			return "redirect:/company/list";
		}
		Employee employee = new Employee();
		employee.setCompany(company);
		employee.setIsEnabled(true);
		model.put("employee", employee);
		model.put("title", "Crear treballador");

		return "/employee/form";
	}

	//@Secured("ROLE_ADMIN")
	@GetMapping("/form/{companyId}/{employeeId}")
	public String edit(@PathVariable(value = "companyId", required = false) Long companyId,
			@PathVariable(value = "employeeId", required = false) Long employeeId, Map<String, Object> model,
			RedirectAttributes flash) {

		Employee employee = null;

		if (employeeId > 0) {
			employee = employeeService.findOne(employeeId);

			if (employee == null) {
				flash.addFlashAttribute("error", "L'identificador del treballador no existeix a la BdD");
				return "redirect:/company/view/{companyId}";
			}
		} else {
			flash.addFlashAttribute("error", "L'identificador del treballador no pot ser zero");
			return "redirect:/company/view/{companyId}";
		}
		model.put("employee", employee);
		model.put("title", "Formulari de Treballador");
		return "/employee/form";
	}

	//@Secured("ROLE_ADMIN")
	@GetMapping(value = "/form")
	public String create(Map<String, Object> model) {
		Employee employee = new Employee();
		employee.setCompany(null);
		model.put("company", employee.getCompany());
		employee.setIsEnabled(true);
		model.put("employee", employee);
		model.put("title", "Crear treballador");
		return "/employee/form";
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Employee employee, BindingResult result,
			@RequestParam(name = "company.id", required = false) Long companyId, Model model, RedirectAttributes flash,
			SessionStatus status) {

		// validator.validate(employee, result);

		if (companyId != null) {
			Company company = new Company();
			company = employeeService.findCompanyById(companyId);

			employee.setCompany(company);

		} else {
			employee.setCompany(null);
			result.rejectValue("company.name", "error.user", "L'empresa informada no existeix");
		}

		if (result.hasErrors()) {
			model.addAttribute("title", "Formulari de Treballador");
			return "/employee/form";
		}

		String flashMessage = (employee.getId() != null) ? "Treballador modificat correctament"
				: "Treballador creat correctament";

		employeeService.save(employee);

		status.setComplete();
		flash.addFlashAttribute("success", flashMessage);

		return "redirect:/company/view/" + employee.getCompany().getId();
	}

	//@Secured("ROLE_USER")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("title", "Llistat de treballadors");
		model.addAttribute("employees", employeeService.findAll());
		return "/employee/list";
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{companyId}/{employeeId}")
	public String delete(@PathVariable(value = "companyId", required = false) Long companyId,
			@PathVariable(value = "employeeId", required = false) Long employeeId, RedirectAttributes flash) {
		if (employeeId > 0) {
			employeeService.delete(employeeId);
			flash.addFlashAttribute("success", "Treballador eliminat correctament");
		}
		return "redirect:/company/view/" + companyId;
	}

}
