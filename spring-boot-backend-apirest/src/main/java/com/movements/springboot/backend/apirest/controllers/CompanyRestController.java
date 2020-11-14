package com.movements.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movements.springboot.backend.apirest.editors.CompanyTypeEditor;
import com.movements.springboot.backend.apirest.editors.LowerCaseEditor;
import com.movements.springboot.backend.apirest.editors.PascalCaseEditor;
import com.movements.springboot.backend.apirest.models.entity.CompanyType;
import com.movements.springboot.backend.apirest.models.entity.Information;
import com.movements.springboot.backend.apirest.models.entity.Company;
import com.movements.springboot.backend.apirest.models.services.ICompanyService;
import com.movements.springboot.backend.apirest.models.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CompanyRestController {

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private CompanyTypeEditor companyTypeEditor;
	
	@Autowired
	private IUploadFileService uploadService;
	
	private final Logger log = LoggerFactory.getLogger(CompanyRestController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(String.class, "name", new PascalCaseEditor());
		binder.registerCustomEditor(CompanyType.class, "companyType", companyTypeEditor);
		binder.registerCustomEditor(String.class, "email", new LowerCaseEditor());
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/companies")
	public List<Company> index() {
		return companyService.findAll();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/companies/page/{page}")
	public Page<Company> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return companyService.findAll(pageable);
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/companies/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Company company = null;
		Map<String, Object> response = new HashMap<>();

		try {
			company = companyService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al realitzar la consulta a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (company == null) {
			response.put("message", "L'empresa ID: ".concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Company>(company, HttpStatus.OK);

	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/companies")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Company company, BindingResult result) {

		Company newCompany = null;
		
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

			newCompany = companyService.save(company);

		} catch (DataAccessException e) {
			
			response.put("message", "Error al realitzar la inserció a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}

		response.put("message", "L'empresa s'ha creat amb èxit");
		response.put("company", newCompany);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PutMapping("/companies/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Company company, 
			BindingResult result, @PathVariable Long id) {

		Company currentCompany = companyService.findById(id);
		Company updatedCompany = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (company == null) {
			response.put("message", "Error: no s'ha pogut editar, l'empresa ID: "
					.concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentCompany.setName(company.getName());
			currentCompany.setPhone(company.getPhone());
			currentCompany.setEmail(company.getEmail());
			currentCompany.setCompanyType(company.getCompanyType());

			updatedCompany = companyService.save(currentCompany);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualitzar l'empresa a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "L'empresa s'ha actualitzat amb èxit");
		response.put("company", updatedCompany);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/companies/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			Company company = companyService.findById(id);
			String oldImageName = company.getImage();
			
			uploadService.delete(oldImageName);
			
			companyService.delete(id);

		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar l'empresa de la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "L'empresa s'ha eliminat amb èxit");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

	
	
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/companies/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		
		Map<String,Object> response = new HashMap<>();
		
		Company company = companyService.findById(id);
		
		if (!file.isEmpty()) {
			
			String imageName = null;
			
			
			try {
				imageName = uploadService.copy(file);
				
			} catch (IOException e) {
				response.put("message", "Error al pujar la imatge");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
			String oldImageName = company.getImage();
			
			uploadService.delete(oldImageName);
			
			company.setImage(imageName);
			companyService.save(company);
			
			response.put("company", company);
			response.put("mensaje", "Has pujat correctament la imatge: " + imageName);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/uploads/img/{imageName:.+}")
	public ResponseEntity<Resource> viewImage(@PathVariable String imageName){
		
		Resource resource = null;
		
		try {
			resource = uploadService.upload(imageName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/company_types")
	public List<CompanyType> companyTypesList(){
		
		return companyService.findAllCompanyTypes();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/company_types/page/{page}")
	public Page<CompanyType> companyTypesList(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 5);
		return companyService.findAllCompanyTypes(pageable);
	}
	


	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/company_types/{idCompanyType}")
	@ResponseStatus(HttpStatus.OK)
	public CompanyType showInformation(@PathVariable(value = "idCompanyType") Long idCompanyType, Model model, RedirectAttributes flash) {

		return companyService.findCompanyTypeById(idCompanyType);
	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/company_types")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createCompanyType (@Valid @RequestBody CompanyType companyType, BindingResult result) {

		CompanyType newCompanyType = null;
		
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

			newCompanyType = companyService.saveCompanyType(companyType);

		} catch (DataAccessException e) {
			
			response.put("message", "Error al realitzar la inserció a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}

		response.put("message", "La informació s'ha creat amb èxit");
		response.put("companyType", newCompanyType);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PutMapping("/company_types/{id}")
	public ResponseEntity<?> updateCompanyType(@Valid @RequestBody CompanyType companyType, 
			BindingResult result, @PathVariable Long id) {

		CompanyType currentCompanyType = companyService.findCompanyTypeById(id);
		CompanyType updatedCompanyType = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El camp '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (companyType == null) {
			response.put("message", "Error: no s'ha pogut editar, la informació ID: "
					.concat(id.toString().concat(" no existeix a la base de dades!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			currentCompanyType.setCode(companyType.getCode());
			currentCompanyType.setDescription(companyType.getDescription());

			updatedCompanyType = companyService.saveCompanyType(updatedCompanyType);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualitzar la informació a la base de dades!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "El tipus d'empresa s'ha actualitzat amb èxit");
		response.put("companyType", updatedCompanyType);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/company_types/{id}")
	public ResponseEntity<?> deleteCompanyType(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();

		
		try {
			
			companyService.deleteCompanyType(id);

		} catch (DataAccessException e) {

			response.put("message", "Error al esborrar tipus d'empresa!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("message", "El tipus d'empresa s'ha esborrat amb èxit");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		

	}
	
}
