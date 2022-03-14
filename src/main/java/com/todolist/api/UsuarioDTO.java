package com.todolist.api;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UsuarioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9037879430316833219L;
	private String uid;
	private String name;
	private String lastname;
	private String role;
	private String country;
	
}
