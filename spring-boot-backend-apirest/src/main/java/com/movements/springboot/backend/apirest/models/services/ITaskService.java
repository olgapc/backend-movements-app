package com.movements.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.movements.springboot.backend.apirest.models.entity.Company;
import com.movements.springboot.backend.apirest.models.entity.Employee;
import com.movements.springboot.backend.apirest.models.entity.Information;
import com.movements.springboot.backend.apirest.models.entity.Task;

public interface ITaskService {	
		
		//tasks methods
		public List<Task> findAll();
		
		public Page<Task> findAll(Pageable pageable);

		public Task save(Task task);
		
		public Task findTaskById(UUID id);
		
		public List<Task> findByIdAndDescription(Long id, String description);
		
		public List<Task> findByUser(String username);
		
		public List<Task> findByUserNull();

		public void delete(UUID id);
		
		//employees methods
		public Employee findEmployeeById(Long id);
		
		//companies methods
		public Company findCompanyById(Long id);
		
		//informations methods
		public List<Information> findInformationByDescription(String term);
		
		public Information findInformationById(Long id);
		
		public void deleteInformation(Long id);
		
		public void deleteTaskInformation(Long id);
		
		public List<Information> findAllInformations();
		
		public Page<Information> findAllInformations(Pageable pageable);
		
		public Information saveInformation(Information information);
		
		public Task fetchByIdWithEmployeeWithCompanyWithTaskInformationWithInformationWithSubtask(Long id);
		
		public Boolean existsByTemplateName(String templateName);
		
		public Boolean informationExistsByDescription(String description);
		
		//subtasks methods
		public List<Task> findAllTemplateTasks();
		
		public Page<Task> findAllTemplateTasks(Pageable pageable);
	
}
