package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class AppUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="no pot estar buit")
	@Size(min=4, max=12, message = "ha de tenir entre 4 i 12 caràcters")
	@Column(unique = true, length = 20)
	private String username;

	@NotEmpty(message="no pot estar buit")
	@Size(min=2, max=20, message = "ha de tenir entre 2 i 20 caràcters")
	@Column(nullable=false)
	private String name;

	@NotEmpty(message="no pot estar buit")
	@Size(min=2, max=20, message = "ha de tenir entre 2 i 20 caràcters")
	@Column(name = "last_name", nullable=false)
	private String lastName;

	@NotEmpty(message="no pot estar buit")
	@Email(message="ha de tenir un format vàlid de mail")
	@Column(unique = true, nullable=false)
	private String email;

	@Column(length = 60, nullable=false)
	@NotEmpty(message="no pot estar buit")
	private String password;

	@Column(name = "is_enabled")
	private Boolean isEnabled;

	private String comment;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createAt;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_fk")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<UserRole> userRoles;
	
	
	public AppUser() {
		userRoles = new ArrayList<UserRole>();
	}
	
	
	
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

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
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

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private static final long serialVersionUID = 1L;
}
