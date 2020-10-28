package com.movements.springboot.backend.apirest.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NifTypes {

	
	DNI("DNI"),
	
	NIE("NIE"), 
	
	PASSPORT("Passaport");

	private final String displayValue;

	NifTypes(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
	
	public static List<NifTypes> orderedValues = new ArrayList<>();
	
	static {
		orderedValues.addAll(Arrays.asList(NifTypes.values()));
	}
}
