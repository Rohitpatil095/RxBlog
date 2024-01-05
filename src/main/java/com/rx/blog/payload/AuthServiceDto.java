package com.rx.blog.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthServiceDto {
	
	String usernameOrEmail;
	String pass;

}
