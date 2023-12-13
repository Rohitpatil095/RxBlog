package com.rx.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public static PasswordEncoder passEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityChain(HttpSecurity security) throws Exception
	{
		return security.csrf().disable()
				.authorizeHttpRequests(
						(auth) -> 
//						auth.anyRequest().authenticated()
						auth.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
						.anyRequest().authenticated()
						)
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
//	@Bean
//	public UserDetailsService users()
//	{
//		UserDetails nonAdmin= User.builder()
//				.username("ramesh")
//				.password(passEncoder().encode("rama"))
//				.roles("USER")
//				.build();
//		UserDetails admin= User.builder()
//				.username("rot")
//				.password(passEncoder().encode("rotten"))
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(nonAdmin, admin);
//	}
	
	
	
}
