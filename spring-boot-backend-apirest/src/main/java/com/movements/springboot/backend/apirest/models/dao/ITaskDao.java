package com.movements.springboot.backend.apirest.models.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.movements.springboot.backend.apirest.models.entity.Task;

public interface ITaskDao extends JpaRepository<Task, Long>{
	
	@Query(nativeQuery = true, value="select * from tasks t "
			+ "left join tasks t2 on t.id = t2.maintask_fk "
			+ "left join task_informations i on  t.id = i.task_fk "
			+ "left join companies c on t.company_fk = c.id "
			+ "left join employees e on t.employee_fk = e.id "
			+ "where t.id = ?#{[0]}")
	public Task fetchByIdWithEmployeeWithCompanyWithTaskInformationWithInformationWithSubtask(Long id);
	
	public Boolean existsByTemplateName(String templateName);
	
	public List<Task> findByIsTemplateTrueAndIsMainTaskTrueOrderByTemplateName();
	
	public Page<Task> findByIsTemplateTrueAndIsMainTaskTrueOrderByTemplateName(Pageable pageable);
	
	public List<Task> findByIsTemplateFalseAndIsMainTaskTrue();
	
	public Page<Task> findByIsTemplateFalseAndIsMainTaskTrue(Pageable pageable);
	
	public List<Task> findByIdAndDescription(Long id, String description);
}
