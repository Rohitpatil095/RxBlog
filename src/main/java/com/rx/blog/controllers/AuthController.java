package com.rx.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rx.blog.payload.AuthServiceDto;
import com.rx.blog.servicesImp.AuthServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	AuthServiceImpl authImpl;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthServiceDto authDto)
	{
		String reponse= authImpl.login(authDto);
		return ResponseEntity.ok(reponse);
	}
}
