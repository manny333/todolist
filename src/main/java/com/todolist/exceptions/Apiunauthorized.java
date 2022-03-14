package com.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED )
public class Apiunauthorized extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8978223453879237147L;

	public Apiunauthorized(String message) {
		super(message);
	}
	
}
