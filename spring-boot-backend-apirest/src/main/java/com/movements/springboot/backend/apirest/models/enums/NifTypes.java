package com.movements.springboot.backend.apirest.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;


public enum NifTypes {

	
	DNI,
	
	NIE, 
	
	PASSPORT;

	NifTypes() {
		
	}

    
}
