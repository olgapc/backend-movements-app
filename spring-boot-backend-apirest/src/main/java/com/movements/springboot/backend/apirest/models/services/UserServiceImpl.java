package com.movements.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

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
//import com.movements.springboot.backend.apirest.models.entity.AppUser;
import com.movements.springboot.backend.apirest.models.entity.Role;
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
	public AppUser findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
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
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
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
	
}
