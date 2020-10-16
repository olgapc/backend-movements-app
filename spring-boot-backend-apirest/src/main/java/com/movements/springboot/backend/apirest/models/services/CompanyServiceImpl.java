package com.movements.springboot.backend.apirest.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movements.springboot.backend.apirest.models.dao.ICompanyTypeDao;
import com.movements.springboot.backend.apirest.models.entity.CompanyType;
import com.movements.springboot.backend.apirest.models.dao.ICompanyDao;
import com.movements.springboot.backend.apirest.models.entity.Company;

@Service
public class CompanyServiceImpl implements ICompanyService{
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICompanyTypeDao companyTypeDao;

	@Override
	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return (List<Company>) companyDao.findAll();
	}

	@Override
	@Transactional
	public Company save(Company company) {
		return companyDao.save(company);
	}

	@Override
	@Transactional(readOnly = true)
	public Company findById(Long id) {
		return companyDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Company fetchByIdWithTasksWithEmployees(Long id) {
		return companyDao.fetchByIdWithTasksWithEmployees(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		companyDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Company> findCompanyByName(String term) {
		return companyDao.findByNameLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyType> findAllCompanyTypes() {
		return (List<CompanyType>) companyTypeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyType findCompanyTypeById(Long id) {
		return companyTypeDao.findById(id).orElse(null);
	}

	
	
	
}