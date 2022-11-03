package com.recipelibrary.api.exception;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("ResourceNotFoundException happened", ex);
		return ErrorMessage.builder()
				.message(ex.getMessage())
				.code(HttpStatus.NOT_FOUND.value()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleResourceNotFoundException(MethodArgumentNotValidException ex) {
		log.error("MethodArgumentNotValidException happened", ex);
		return ErrorMessage.builder()
				.message(ex.getMessage())
				.code(HttpStatus.BAD_REQUEST.value()).build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleResourceNotFoundException(HttpMessageNotReadableException ex) {
		log.error("HttpMessageNotReadableException happened", ex);
		return ErrorMessage.builder()
				.message("Required request body is missing.")
				.code(HttpStatus.BAD_REQUEST.value()).build();
	}
}
