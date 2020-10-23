package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movements.springboot.backend.apirest.models.enums.NifType;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "employee_name")
	//@NotEmpty
	//@Size(min = 2, max = 50)
	private String name;

	//@Pattern(regexp = "[X-Y]?[0-9]{8}[A-Z]?")
	private String nif;
	
	@Column(name="nif_type")
	private NifType nifType;

	//@Pattern(regexp = "[\\d]{1,2}[-][\\d]{7,8}[-][\\d]{2}")
	private String naf;

	@Email
	private String email;

	private String phone;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;	
	
	@NotEmpty
	private String gender;
	
	private Boolean isEnabled;
	
	private String comment;
	
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createAt;

	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"employee", "hibernateLazyInitializer", "handler", "company"})
	private List<Task> tasks;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_fk")
	@JsonIgnoreProperties(value={"employees", "hibernateLazyInitializer", "handler", "tasks"})
	private Company company;
	

	public Employee() {
		tasks = new ArrayList<Task>();
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public NifType getNifType() {
		return nifType;
	}

	public void setNifType(NifType nifType) {
		this.nifType = nifType;
	}
	
	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
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

	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@Override
	public String toString() {
		return name.toUpperCase()+ ". \n NIF: " + nif + ", NAF:" + naf + ", " + email;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null)? 0: id.hashCode());
		result = prime * result + ((name == null)? 0 : name.hashCode());
		result = prime * result + ((nif == null)? 0 : nif.hashCode());
		result = prime * result + ((nifType == null)? 0 : nifType.hashCode());
		result = prime * result + ((naf == null)? 0 : naf.hashCode());
		result = prime * result + ((email == null)? 0 : email.hashCode());
		result = prime * result + ((phone == null)? 0 : phone.hashCode());
		result = prime * result + ((birthDate == null)? 0 : birthDate.hashCode());
		result = prime * result + ((gender == null)? 0 : gender.hashCode());
		result = prime * result + (isEnabled ? 1231 : 1237);
		result = prime * result + ((comment == null)? 0 : comment.hashCode());
		
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
		
		Employee other = (Employee) obj;
		
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
		
		if(nif == null) {
			if (other.nif == null) {
				return false;
			}
		} else if (!nif.equals(other.nif)) {
			return false;
		}
		
		if(nifType != other.nifType) {
			return false;
		}
		
		if(naf == null) {
			if (other.naf == null) {
				return false;
			}
		} else if (!naf.equals(other.naf)) {
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
		
		if(birthDate == null) {
			if (other.birthDate == null) {
				return false;
			}
		} else if (!birthDate.equals(other.birthDate)) {
			return false;
		}
		
		if(gender != other.gender) {
			return false;
		}
		
		if (isEnabled != other.isEnabled) {
			return false;
		}
		
		if(comment == null) {
			if (other.comment == null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		
		return true;
	}




	private static final long serialVersionUID = 1L;
}
