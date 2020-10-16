package com.movements.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.movements.springboot.backend.apirest.models.entity.CompanyType;

public interface ICompanyTypeDao extends CrudRepository<CompanyType, Long>{

	
	
}
