package com.movements.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.movements.springboot.backend.apirest.models.entity.CompanyType;
import com.movements.springboot.backend.apirest.models.entity.Company;

public interface ICompanyService {
	
	public List<Company> findAll();
	
	public Page<Company> findAll(Pageable pageable);

	public Company save(Company company);

	public Company findById(Long id);

	public Company fetchByIdWithTasksWithEmployees(Long id);
	
	public void delete(Long id);
	
	public List<Company> findCompanyByName(String term);

	
	public CompanyType findCompanyTypeById(Long id);
	
	public List<CompanyType> findAllCompanyTypes();

}
