package com.dieldev.ecommerce.client.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private HttpStatus httpStatus;
	private String message;

	public CustomException(String message, HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
