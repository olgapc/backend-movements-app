package com.movements.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;

import com.movements.springboot.backend.apirest.models.entity.Information;

public interface IInformationDao extends JpaRepository<Information, Long>{

	//@Query("select i from Information i where i.description like %?1%")
	//public List<Information> findByDescription(String term);
	
	public List<Information> findByDescriptionContainingIgnoreCase(String term);
	
	
}
