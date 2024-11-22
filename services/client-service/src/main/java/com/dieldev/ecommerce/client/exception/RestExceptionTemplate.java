package com.dieldev.ecommerce.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RestExceptionTemplate {
	private HttpStatus httpStatus;
	private String message;
	private LocalDateTime errorTime;
}
