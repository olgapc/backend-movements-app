package com.movements.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movements.springboot.backend.apirest.models.dao.ICompanyDao;
import com.movements.springboot.backend.apirest.models.dao.IEmployeeDao;
import com.movements.springboot.backend.apirest.models.dao.IInformationDao;
import com.movements.springboot.backend.apirest.models.dao.ITaskDao;
import com.movements.springboot.backend.apirest.models.entity.Company;
import com.movements.springboot.backend.apirest.models.entity.Employee;
import com.movements.springboot.backend.apirest.models.entity.Information;
import com.movements.springboot.backend.apirest.models.entity.Task;

@Service
public class TaskServiceImpl implements ITaskService{

	
	@Autowired
	private ITaskDao taskDao;
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired
	private IInformationDao informationDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Task> findAll() {		
		return (List<Task>) taskDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<Task> findAll(Pageable pageable) {
		return taskDao.findAll(pageable);
	}

	
	@Override
	@Transactional
	public Task save(Task task) {
		return taskDao.save(task);	
	}

	@Override
	@Transactional
	public void delete(Long id) {
		taskDao.deleteById(id);	
	}

	@Override
	@Transactional (readOnly=true)
	public Employee findEmployeeById(Long id) {
		return employeeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional (readOnly=true)
	public Company findCompanyById(Long id) {
		return companyDao.findById(id).orElse(null);
	}

	@Override
	@Transactional (readOnly=true)
	public List<Information> findInformationByDescription(String term) {
		return informationDao.findByDescriptionContainingIgnoreCase(term);
	}

	@Override
	@Transactional (readOnly=true)
	public Information findInformationById(Long id) {
		return informationDao.findById(id).orElse(null);
	}

	@Override
	@Transactional (readOnly=true)
	public Task findTaskById(Long id) {
		return taskDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteInformation(Long id) {
		informationDao.deleteById(id);
	}

	@Override
	@Transactional (readOnly=true)
	public List<Information> findAllInformations() {
		return (List<Information>) informationDao.findAll();
	}

	
	
	@Override
	@Transactional (readOnly=true)
	public Page<Information> findAllInformations(Pageable pageable) {
		return informationDao.findAll(pageable);
	}


	@Override
	@Transactional
	public Information saveInformation(Information information) {
		return informationDao.save(information);
	}

	@Override
	@Transactional(readOnly=true)
	public Task fetchByIdWithEmployeeWithCompanyWithTaskInformationWithInformationWithSubtask(Long id) {
		return taskDao.fetchByIdWithEmployeeWithCompanyWithTaskInformationWithInformationWithSubtask(id);
	}
	
	
	
}
