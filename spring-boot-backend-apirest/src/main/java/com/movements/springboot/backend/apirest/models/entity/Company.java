package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
//import javax.persistence.PrePersist;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.springframework.format.annotation.DateTimeFormat;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "companies")
public class Company implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name")
	@NotEmpty(message = "no pot estar buit")
	@Size(min = 2, max = 50, message = "ha de tenir entre 2 i 50 caràcters")
	private String name;

	@NotEmpty(message = "no pot estar buit")
	@Email(message = "no és una adreça de correu correcta")
	private String email;

	@NotEmpty(message = "no pot estar buit") // notEmpty només per Strings, pels demés tipus notnull
	private String phone;

	@Column(name = "create_at", columnDefinition= "TIMESTAMP")
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime createAt;

	private String image;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull (message="no pot estar buit")
	@JoinColumn(name = "company_type_fk")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private CompanyType companyType;

	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"company", "hibernateLazyInitializer", "handler", "tasks", "subtasks", "mainTask"}, allowSetters=true)
	private List<Employee> employees;

	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"company", "hibernateLazyInitializer", "handler", "employee", "subtasks", "mainTask"}, allowSetters=true)
	private List<Task> tasks;

	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}

	public Company() {
		employees = new ArrayList<Employee>();
		tasks = new ArrayList<Task>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		if(employees.contains(employee)) 
		return ;
		employees.add(employee);
		employee.setCompany(this);
	}
	
	public void removeEmployee(Employee employee) {
		if (!employees.contains(employee)) {
			return;
		}
		employees.remove(employee);
		employee.setCompany(null);
	}

	public void addTask(Task task) {
		if (tasks.contains(task)) 
				return ;
		tasks.add(task);
		task.setCompany(this);
	}

	
	
	@Override
	public String toString() {
		return name.toUpperCase() + ",\n " + email + ", T. " + phone;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null)? 0: id.hashCode());
		result = prime * result + ((name == null)? 0 : name.hashCode());
		result = prime * result + ((email == null)? 0 : email.hashCode());
		result = prime * result + ((phone == null)? 0 : phone.hashCode());
		result = prime * result + ((image == null)? 0 : image.hashCode());
		result = prime * result + ((companyType == null)? 0 : companyType.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || !(obj instanceof Task)) {
			return false;
		}
		
		Company other = (Company) obj;
		
		if(id == null) {
			if (other.id == null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		if(name == null) {
			if (other.name == null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		
		if(email == null) {
			if (other.email == null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}		
		
		if(phone == null) {
			if (other.phone == null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}		
		
		if(image == null) {
			if (other.image == null) {
				return false;
			}
		} else if (!image.equals(other.image)) {
			return false;
		}
		
		if(companyType == null) {
			if (other.companyType == null) {
				return false;
			}
		} else if (!companyType.equals(other.companyType)) {
			return false;
		}
		
		return true;
	}




	private static final long serialVersionUID = 1L;

}