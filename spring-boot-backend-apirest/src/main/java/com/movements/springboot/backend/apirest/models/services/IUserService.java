package com.movements.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.movements.springboot.backend.apirest.models.entity.Role;
import com.movements.springboot.backend.apirest.models.entity.AppUser;

public interface IUserService {
	
	public AppUser findByUsername (String username);
	
	public AppUser findByEmail (String email);
	
	public List<AppUser> findAll();
	
	public Page<AppUser> findAll(Pageable pageable);

	public AppUser save(AppUser user);

	public AppUser findById(Long id);
	
	public void delete(Long id);

	public List<Role> findAllRoles();
	
	public Role findRoleById(Long id);
	
	public Boolean existsByEmail(String email);
	
	public Boolean existsByUsername(String username);
	
}
