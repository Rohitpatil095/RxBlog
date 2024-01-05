package com.rx.blog.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rx.blog.payload.AuthServiceDto;
import com.rx.blog.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authManager;
	
	
	public String login(AuthServiceDto authDto) {
		
		Authentication auth= authManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsernameOrEmail(), authDto.getPass()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "User logged in success";
	}

}
