package com.rx.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlogApiException(HttpStatus status,String msg)
	{
		super(String.format("%s %s",""+status,msg));
	}
}
