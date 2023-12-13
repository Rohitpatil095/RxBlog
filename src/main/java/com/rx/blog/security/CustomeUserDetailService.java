package com.rx.blog.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rx.blog.entity.Users;
import com.rx.blog.repository.UserRepository;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
		
		Users user=uRepo
				.findByUsernameOrEmail(usernameoremail, usernameoremail)
				.orElseThrow(
						()-> new UsernameNotFoundException("User with given username or email not present"+usernameoremail));
		
		Set<GrantedAuthority> authority= user
				.getRoles()
				.stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		
		return new User(user.getEmail(),user.getPassword(),authority);
	}


	

}
