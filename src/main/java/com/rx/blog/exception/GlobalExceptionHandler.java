package com.rx.blog.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rx.blog.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//handling specific exceptions 
	@ExceptionHandler(ResourceNotdFoundException.class)
	public ResponseEntity<ErrorDetails> handleReosourceNotFoundException(ResourceNotdFoundException exception, WebRequest request)
	{
		ErrorDetails errorDetail= new ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetail,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetails> handleBlogApiException(BlogApiException exception, WebRequest request)
	{
		ErrorDetails errorDetail= new ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetail,HttpStatus.BAD_REQUEST);
	}
	
	
	// handling global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request)
	{
		ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
