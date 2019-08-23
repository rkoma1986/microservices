package com.example.api.error;

public class ApiValidationError extends ApiSubError {

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
}
