package com.parsakav.echorestapi.security;

import java.io.IOException;
import java.util.ArrayList;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken uToken = getAuthetication(request);
		SecurityContextHolder.getContext().setAuthentication(uToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthetication(HttpServletRequest request) {
		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		try {
			if (header != null) {

				String username = Jwts.parserBuilder().setSigningKey(SecurityConstant.getSigningKey()).build()
						.parseClaimsJws(header.replace("Bearer", "").trim()).getBody().getSubject();
				System.out.println(username);
				// TODO Auto-generated method stub
				if (username != null) {
					return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
				}
			}
		} catch (Exception e) {

		}
		return null;
	}
}
