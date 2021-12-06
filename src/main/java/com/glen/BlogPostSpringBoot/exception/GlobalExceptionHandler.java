package com.glen.BlogPostSpringBoot.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import com.glen.BlogPostSpringBoot.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler{
	
	//Specific exception handler
	@ExceptionHandler(ResourceNotFoundException.class)
	public  ResponseEntity<ErrorDetails> handleResourceNotFundException(
			ResourceNotFoundException exception,
			WebRequest webRequest
	){
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(),
				exception.getMessage(),
				webRequest.getDescription(false)
		);
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BlogApiException.class)
	public  ResponseEntity<ErrorDetails> handleBlogApiException(
			BlogApiException exception,
			WebRequest webRequest
	){
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(),
				exception.getMessage(),
				webRequest.getDescription(false)
		);
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	//Specific exception handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public  ResponseEntity<Object> handleMethodArguementNotValidException(
			MethodArgumentNotValidException exception,
			WebRequest webRequest
	){
		Map<String,String>errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach(error->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	//Global exception handler
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<ErrorDetails> handleGeneralException(
			Exception exception,
			WebRequest webRequest
	){
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(),
				exception.getMessage(),
				webRequest.getDescription(false)
		);
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	

}
