package com.movements.springboot.backend.apirest.models.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movements.springboot.backend.apirest.models.entity.Employee;

public interface IEmployeeDao extends JpaRepository<Employee, Long>{

	public List<Employee> findByNameLikeIgnoreCase(String term);
	
	@Query("select e from Employee e where e.company.id = ?1 and e.name like %?2%")
	public List<Employee> findByCompanyIdAndName(Long id, String term);
	
	@Query("select e from Employee e left join fetch e.tasks t join fetch e.company where e.id=?1")
	public Employee fetchByIdWithTasksWithCompany(Long id);
	
}
