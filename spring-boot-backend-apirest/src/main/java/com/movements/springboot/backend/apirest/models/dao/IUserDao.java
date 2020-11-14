package com.movements.springboot.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.movements.springboot.backend.apirest.models.entity.AppUser;


public interface IUserDao extends JpaRepository<AppUser, Long>{

	public AppUser findByUsername(String username);
	
	public AppUser findByEmail(String email);
	
}
