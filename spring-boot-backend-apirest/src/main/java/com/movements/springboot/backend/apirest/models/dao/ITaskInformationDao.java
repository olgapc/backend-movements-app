package com.movements.springboot.backend.apirest.models.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movements.springboot.backend.apirest.models.entity.TaskInformation;

public interface ITaskInformationDao extends JpaRepository<TaskInformation, Long>{
	
	

}
