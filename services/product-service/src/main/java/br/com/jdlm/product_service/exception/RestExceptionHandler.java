package br.com.jdlm.product_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<RestExceptionTemplate> CustomException(CustomException exception) {
        return new ResponseEntity<>(new RestExceptionTemplate(exception.getHttpStatus(), exception.getMessage(), LocalDateTime.now()), exception.getHttpStatus());
    }
}
