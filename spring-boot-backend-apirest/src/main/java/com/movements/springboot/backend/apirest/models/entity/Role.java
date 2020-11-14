package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(unique = true, length = 20)
	private String role;

	/*
	 * @ManyToMany(mappedBy = "roles")
	 * 
	 * @JsonIgnoreProperties(value = { "roles", "hibernateLazyInitializer",
	 * "handler" }, allowSetters = true) private List<AppUser> users;
	 */

	private String comment;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createAt;

	public Role() {

	}

	public Role(String description, String role, LocalDateTime createAt) {
		this.description = description;
		this.role = role;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/*
	 * public List<AppUser> getUsers() { return users; }
	 * 
	 * public void setUsers(List<AppUser> users) { this.users = users; }
	 */
	
	
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

	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}

	private static final long serialVersionUID = 1L;

}
