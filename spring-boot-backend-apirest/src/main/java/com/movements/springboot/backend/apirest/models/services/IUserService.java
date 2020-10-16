package com.movements.springboot.backend.apirest.models.services;

import java.util.List;

import com.movements.springboot.backend.apirest.models.entity.Role;
import com.movements.springboot.backend.apirest.models.entity.AppUser;

public interface IUserService {
	
	public AppUser findByUsername (String username);
	
	public AppUser findByEmail (String email);
	
	public List<AppUser> findAll();

	public AppUser save(AppUser user);

	public AppUser findById(Long id);
	
	public void delete(Long id);

	public List<Role> listRoles();
	
	public Role findRoleById(Long id);
	
}
