package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.MyUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtFilter jwtFilter;
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
    	return http
    			.csrf(customizer-> customizer.disable())
			.authorizeHttpRequests(request->
								request.requestMatchers("/login").permitAll()
								.anyRequest().authenticated()
								)
    							
    			.httpBasic(Customizer.withDefaults())
    			.sessionManagement(session-> 
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    			.build();
	}
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    	provider.setUserDetailsService(myUserDetailsService);
    	
    	return provider;
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
  
		return authenticationConfiguration.getAuthenticationManager();
    }
}
