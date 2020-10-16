package com.movements.springboot.backend.apirest.models.enums;

public enum NifType {

	DNI("DNI"), NIE("NIE"), PASSPORT("Passaport");

	private String displayValue;

	NifType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
}
