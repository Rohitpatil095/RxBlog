package com.rx.blog.services;

import com.rx.blog.payload.AuthServiceDto;

public interface AuthService {
	
	String login(AuthServiceDto authDto);

}
