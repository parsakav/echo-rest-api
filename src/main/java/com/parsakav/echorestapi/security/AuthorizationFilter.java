package com.parsakav.echorestapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.parsakav.echorestapi.SpringApplicationContext;
import com.parsakav.echorestapi.service.RoleService;
import com.parsakav.echorestapi.service.RoleServiceImpl;
import com.parsakav.echorestapi.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;

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
		RoleService roleService= (RoleService) SpringApplicationContext.getBean("roleServiceImpl");
		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		try {
			if (header != null) {

				String username = Jwts.parserBuilder().setSigningKey(SecurityConstant.getSigningKey()).build()
						.parseClaimsJws(header.replace("Bearer", "").trim()).getBody().getSubject();
				// TODO Auto-generated method stub
				if (username != null) {
					System.out.println("Hi");
					List<GrantedAuthority> roles = new ArrayList<>();
					System.out.println(roleService.findRole(username));
					roles.add(new SimpleGrantedAuthority(roleService.findRole(username)));
					System.out.println("p");
					return new UsernamePasswordAuthenticationToken(username, null, roles);
				}
			}
		} catch (Exception e) {
e.printStackTrace();

		}
		return null;
	}
}
