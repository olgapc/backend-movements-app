package com.movements.springboot.backend.apirest.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.movements.springboot.backend.apirest.models.entity.AppUser;


public interface IUserDao extends CrudRepository<AppUser, Long>{

	public AppUser findByUsername(String username);
	
	public AppUser findByEmail(String email);
	
}
