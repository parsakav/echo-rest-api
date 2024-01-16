package com.parsakav.echorestapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authProvider,
			AuthenticationManager authenticationManager, UserDetailsService userDetailsService) throws Exception {

		http.authenticationProvider(authProvider);

		http.csrf((c)->c.disable()).authorizeHttpRequests((c)->{
					try {

						c.requestMatchers(HttpMethod.POST, SecurityConstant.SIGN_UP_URL)
										.permitAll()
								.requestMatchers(  "/v3/api-docs/**","/swagger-ui/**").permitAll()
								.requestMatchers("/api/comment/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/api/business").permitAll()
								.requestMatchers(HttpMethod.GET,"/api/business").permitAll()
								.requestMatchers("/api/utils/**").permitAll()

								.requestMatchers(HttpMethod.GET, "/api/influencer").permitAll()
								.requestMatchers(HttpMethod.GET, "/api/influencer/search/**").permitAll()
								.anyRequest().authenticated()
										.and()
										.sessionManagement((session)->{

											session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
										})


										.addFilter(getAuthenticationFilter(authenticationManager))
										.addFilter(new AuthorizationFilter(authenticationManager));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}

				});

		return http.build();

	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetails, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetails);
		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}

	public AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);

		authenticationFilter.setFilterProcessesUrl("/users/login");
		return authenticationFilter;
	}
}
