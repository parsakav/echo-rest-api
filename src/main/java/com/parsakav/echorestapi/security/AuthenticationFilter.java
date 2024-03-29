package com.parsakav.echorestapi.security;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.parsakav.echorestapi.SpringApplicationContext;
import com.parsakav.echorestapi.request.UserLoginRequest;
import com.parsakav.echorestapi.service.RoleService;
import com.parsakav.echorestapi.service.RoleServiceImpl;
import com.parsakav.echorestapi.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	@Autowired
	private RoleService roleService;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {

			UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(),
					UserLoginRequest.class);


			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getPhoneNumber(), creds.getPassword(), null));
		}catch (com.fasterxml.jackson.databind.exc.MismatchedInputException e){
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {

			String username=((User) authResult.getPrincipal()).getUsername();
			String token = Jwts.builder().setSubject(username)
					.setExpiration(dateAfterNow(SecurityConstant.EXPIRATION_TIME))
					.signWith(SecurityConstant.getSigningKey())
					.compact();

	/*	UserDetailsService userService=(UserDetailsService) SpringApplicationContext.getBean("UserDetailsImpl");
			UserDto user=userService.getUser(username);*/
			response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX+token);
/*
			response.addHeader("UserId", user.getUserId());
*/



	}


	private Date dateAfterNow(long millis) {
		return new Date(System.currentTimeMillis()+millis);
	}
}
