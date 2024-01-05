package com.rx.blog.servicesImp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rx.blog.entity.Roles;
import com.rx.blog.entity.Users;
import com.rx.blog.exception.BlogApiException;
import com.rx.blog.payload.AuthServiceDto;
import com.rx.blog.payload.RegisterUserDto;
import com.rx.blog.repository.RolesRepository;
import com.rx.blog.repository.UserRepository;
import com.rx.blog.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RolesRepository rolesRepo;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	
	public String login(AuthServiceDto authDto) {
		
		Authentication auth= authManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsernameOrEmail(), authDto.getPass()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "User logged in success";
	}


	@Override
	public String register(RegisterUserDto regUserDto) {

		// check is user already present
		if(userRepo.existsByEmail(regUserDto.getEmail()))
		{
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"Same usermail already present!");
		}
		if(userRepo.existsByUsername(regUserDto.getUsername()))
		{
			throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Same user already present!");
		}
		
		Users user=new Users();
		BeanUtils.copyProperties(regUserDto, user, "pass");
		user.setPassword(passEncoder.encode(regUserDto.getPass()));
		
		Set<Roles> roles=new HashSet<>();
		
		roles.add(rolesRepo.findByName("ROLE_USER").get());
		
		user.setRoles(roles);
		userRepo.save(user);
		
		return "User registered successful!";
	}

}
