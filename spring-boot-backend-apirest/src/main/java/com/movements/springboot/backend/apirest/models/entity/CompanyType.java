package com.movements.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_types")
public class CompanyType implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String code;
	
	private String description;

	
	
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

	public void setName(String description) {
		this.description = description;
	}

	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null)? 0: id.hashCode());
		result = prime * result + ((code == null)? 0 : code.hashCode());
		result = prime * result + ((description == null)? 0 : description.hashCode());

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
		
		if(id == null) {
			if (other.id == null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		
		if(code == null) {
			if (other.code == null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		
		if(description == null) {
			if (other.description == null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		
		return true;
	}





	private static final long serialVersionUID = 1L;
}
