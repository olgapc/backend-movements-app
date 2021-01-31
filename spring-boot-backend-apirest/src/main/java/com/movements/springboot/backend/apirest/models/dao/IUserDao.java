package com.movements.springboot.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.movements.springboot.backend.apirest.models.entity.AppUser;


public interface IUserDao extends JpaRepository<AppUser, UUID>{

	public AppUser findByUsername(String username);
	
	public AppUser findByEmail(String email);
	
	public Boolean existsByEmail(String email);
	
	public Boolean existsByUsername(String username);
	
}
