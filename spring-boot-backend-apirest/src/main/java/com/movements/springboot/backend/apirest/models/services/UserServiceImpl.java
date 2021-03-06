package com.movements.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movements.springboot.backend.apirest.models.dao.IRoleDao;
import com.movements.springboot.backend.apirest.models.dao.IUserDao;
import com.movements.springboot.backend.apirest.models.entity.Role;
import com.movements.springboot.backend.apirest.models.entity.UserRole;
import com.movements.springboot.backend.apirest.models.entity.AppUser;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IRoleDao roleDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<AppUser> findAll() {
		return (List<AppUser>) userDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<AppUser> findAll(Pageable pageable) {
		return userDao.findAll(pageable);
	}

	@Override
	@Transactional
	public AppUser save(AppUser user) {
		return userDao.save(user);	
	}

	@Override
	@Transactional(readOnly = true)
	public AppUser findById(UUID id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		userDao.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAllRoles() {
		return (List<Role>) roleDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Role findRoleById(Long id) {
		return roleDao.findById(id).orElse(null);
	}


	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser user = userDao.findByUsername(username);
		
		if(user == null) {
			logger.error("Error en el login: no existeix l\'usuari '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existeix l\'usuari '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (UserRole userRole : user.getUserRoles()) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRole()));
		}
		
		authorities.stream()
		.peek(authority ->
		logger.info("Role: " + authority.getAuthority()));
		
		
		/*
		 * List<GrantedAuthority> authorities = user.getRoles() .stream() .map(role ->
		 * new SimpleGrantedAuthority(role.getRole())) .peek(authority ->
		 * logger.info("Role: " + authority.getAuthority()))
		 * .collect(Collectors.toList());
		 */
		
		return new User(user.getUsername(), user.getPassword(), user.getIsEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public AppUser findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	public AppUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public Boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public Boolean existsByUsername(String username) {
		return userDao.existsByUsername(username);
	}
	
	
	
}
