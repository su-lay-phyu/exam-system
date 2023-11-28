package com.nexcode.examsystem.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(value= {RuntimeException.class})
	 public ResponseEntity<?>handleRuntimeException(RuntimeException e)
	 {
		 throw e;
	 }
	 @ExceptionHandler(ExpiredJwtException.class)
	    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
	        // Customize the response for expired JWT
	        String errorMessage = "JWT token has expired";
	        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
	    }

}
