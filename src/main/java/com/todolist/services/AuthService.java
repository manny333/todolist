package com.todolist.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.todolist.api.UsuarioDTO;
import com.todolist.dto.JwtResponse;
import com.todolist.security.JwtIO;
import com.todolist.util.DateUtils;

@Service
public class AuthService {
	
	@Autowired
	private JwtIO jwtIO;
	
	@Autowired
	private DateUtils dateUtils;
	
	@Value("${jms.jwt.token.expires-in}")
	private int EXPIRES_IN;
	
	public JwtResponse login(String clientId,String clientSecret) {
		
		UUID uuid = UUID.randomUUID();
		
		UsuarioDTO user = UsuarioDTO.builder()
				.name("Emmanuel")
				.lastname("Hernandez")
				.role("ADMIN")
				.country("Mexico")
				.uid(uuid.toString())
				.build();
		
		JwtResponse jwt = JwtResponse.builder()
				.tokenType("bearer")
				.accessToken(jwtIO.generateToken("Hola mundo"))
				.issuedAt(dateUtils.getDateMillis()+"")
				.clientId(clientId)
				.expiresIn(3600)
				.build();
		return jwt; 
	}
	
}
