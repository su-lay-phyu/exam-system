package com.nexcode.examsystem.model.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(value= {RuntimeException.class})
	 public ResponseEntity<?>handleRuntimeException(RuntimeException e)
	 {
		 throw e;
	 }

}
