package com.linor.todo.validation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ToDoValidationError {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errors = new ArrayList<>();

	private final String errorMessage;
	
	public ToDoValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void addValidationError(String error) {
		errors.add(error);
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
