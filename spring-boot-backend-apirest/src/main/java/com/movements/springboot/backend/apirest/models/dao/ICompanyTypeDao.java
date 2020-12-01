package com.movements.springboot.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.movements.springboot.backend.apirest.models.entity.CompanyType;

public interface ICompanyTypeDao extends JpaRepository<CompanyType, Long>{

	public Boolean existsByCode(String code);
	
	public Boolean existsByDescription(String description);
	
}
