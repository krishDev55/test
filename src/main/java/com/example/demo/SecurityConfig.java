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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.service.MyUserDetailsService;



@Configuration
//@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
//	
	@Autowired
	JwtFilter jwtFilter;
 
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(customizer-> customizer.disable())
				.authorizeHttpRequests(request-> request
												.requestMatchers(
																"/v1/app/login",
																"/v1/app/register",
																"/v1/app/SendOtp/{}").permitAll()
												.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session-> 
							session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.formLogin(Customizer.withDefaults())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.cors(cors -> {
	                cors.configurationSource(corsConfigurationSource());
	            })
				.build();
	}

    @SuppressWarnings("deprecation")
	@Bean
   public  AuthenticationProvider authenticationProvider() {  
    	DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    	provider.setUserDetailsService(myUserDetailsService);
    	
    	return provider;
    }
    
    
    @Bean
  public   AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
  
		return authenticationConfiguration.getAuthenticationManager();
    }
    
    
    
//    @Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user1= User
//							.withDefaultPasswordEncoder()
//							.username("kiran")
//							.password("abcd")
//							.roles("USER")
//							.build();
//		
//		return new InMemoryUserDetailsManager(user1);
//	}
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://actshopmoney.netlify.app"); 
        configuration.addAllowedMethod("*"); 
        configuration.addAllowedHeader("*"); 
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new 
         UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
