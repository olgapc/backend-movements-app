package com.movements.springboot.backend.apirest.editors;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PascalCaseEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		String newValue = text.trim();
		if (newValue.length() > 0) {
			

			newValue = Arrays.stream(newValue.split("\\s"))
					.map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
					.collect(Collectors.joining(" "));
			newValue = Arrays.stream(newValue.split("\\,")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
					.collect(Collectors.joining(", "));
			newValue = Arrays.stream(newValue.split("\\.")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
					.collect(Collectors.joining("."));
			setValue(newValue);
		}

	}

}
