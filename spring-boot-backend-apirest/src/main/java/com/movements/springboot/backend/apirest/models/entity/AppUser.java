package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.UniqueConstraint;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

//import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class AppUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;
	
	private String name;
	
	@Column(name= "last_name")
	private String lastName;
	
	
	@Column(unique = true)
	private String email;

	@Column(length = 60)
	private String password;

	private Boolean enabled;

	@Column(name = "create_at", columnDefinition= "TIMESTAMP")
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime createAt;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_fk"),
	inverseJoinColumns=@JoinColumn(name="role_fk"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"user_fk", "role_fk"})})
	@JsonIgnoreProperties(value={"users", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	private static final long serialVersionUID = 1L;
}
