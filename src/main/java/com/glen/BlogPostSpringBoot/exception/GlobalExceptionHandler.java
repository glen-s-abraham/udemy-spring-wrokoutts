package com.glen.BlogPostSpringBoot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.glen.BlogPostSpringBoot.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	
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
