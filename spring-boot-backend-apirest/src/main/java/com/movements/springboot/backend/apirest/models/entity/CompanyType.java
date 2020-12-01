package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company_types")
public class CompanyType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable= false, unique= true)
	@NotEmpty(message = "no pot estar buit")
	@Size(min=2, max=6, message = "ha de tenir entre 2 i 6 caràcters")
	private String code;

	@Column(nullable= false, unique= true)
	@NotEmpty(message = "no pot estar buit")
	@Size(min=5, max=50, message = "ha de tenir entre 5 i 50 caràcters")
	private String description;

	private String comment;

	@Column(name = "create_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createAt;

	public CompanyType() {

	}

	public CompanyType(Long id, String code, String description) {
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public CompanyType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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

		CompanyType other = (CompanyType) obj;

		if (id == null) {
			if (other.id == null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		if (code == null) {
			if (other.code == null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}

		if (description == null) {
			if (other.description == null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
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
