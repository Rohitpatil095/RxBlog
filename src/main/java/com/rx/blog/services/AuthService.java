package com.rx.blog.services;

import com.rx.blog.payload.AuthServiceDto;
import com.rx.blog.payload.RegisterUserDto;

public interface AuthService {
	
	String login(AuthServiceDto authDto);
	
	String register(RegisterUserDto regUserDto);

}
