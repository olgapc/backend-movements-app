package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movements.springboot.backend.apirest.models.enums.Gender;
import com.movements.springboot.backend.apirest.models.enums.NifTypes;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "employee_name")
	@NotEmpty(message = "no pot estar buit")
	private String name;

	private String nif;

	@Column(name = "nif_type", nullable = true, length = 8)
	@Enumerated(EnumType.STRING)
	private NifTypes nifType;

	private String naf;

	@Email
	private String email;

	private String phone;

	@Past
	@Column(name = "birth_date", columnDefinition = "DATE")
	private LocalDate birthDate;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private Boolean isEnabled;

	private String comment;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP")

	private LocalDateTime createAt;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "employee", "hibernateLazyInitializer", "handler", "company", "subtasks",
			"mainTask" }, allowSetters = true)
	private List<Task> tasks;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonProperty("company")
	@JoinColumn(name = "company_fk")
	@JsonIgnoreProperties(value = { "employees", "hibernateLazyInitializer", "handler", "tasks",
			"mainTask" }, allowSetters = true)
	private Company company;

	public Employee() {
		tasks = new ArrayList<Task>();
		nifType = nifType.DNI;

	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if (sameAsInit(company))
			return;
		Company oldCompany = this.company;
		this.company = company;
		if (oldCompany != null) {
			oldCompany.removeEmployee(this);

		}
		if (company != null) {
			company.addEmployee(this);
		}
	}

	private boolean sameAsInit(Company newCompany) {
		return company == null ? newCompany == null : company.equals(newCompany);
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

	public NifTypes getNifType() {
		return nifType;
	}

	public void setNifType(NifTypes nifType) {
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return name.toUpperCase() + ". \n NIF: " + nif + ", NAF:" + naf + ", " + email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((nifType == null) ? 0 : nifType.hashCode());
		result = prime * result + ((naf == null) ? 0 : naf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (isEnabled ? 1231 : 1237);
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());

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

		if (id == null) {
			if (other.id == null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		if (name == null) {
			if (other.name == null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}

		if (nif == null) {
			if (other.nif == null) {
				return false;
			}
		} else if (!nif.equals(other.nif)) {
			return false;
		}

		if (nifType != other.nifType) {
			return false;
		}

		if (naf == null) {
			if (other.naf == null) {
				return false;
			}
		} else if (!naf.equals(other.naf)) {
			return false;
		}

		if (email == null) {
			if (other.email == null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}

		if (phone == null) {
			if (other.phone == null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}

		if (birthDate == null) {
			if (other.birthDate == null) {
				return false;
			}
		} else if (!birthDate.equals(other.birthDate)) {
			return false;
		}

		if (gender != other.gender) {
			return false;
		}

		if (isEnabled != other.isEnabled) {
			return false;
		}

		if (comment == null) {
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
