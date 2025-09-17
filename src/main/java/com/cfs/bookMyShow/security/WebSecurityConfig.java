package com.cfs.bookMyShow.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private  final   JwtAuthFilter jwtAuthFilter ;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  configuration) throws Exception {
       return   configuration.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity  security) throws  Exception{
        security
                .csrf(csrf->csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests(
                outh->outh
                        .requestMatchers("/movie/**", "/auth/**").permitAll()
//                        .requestMatchers("/booking/**").authenticated()
                        .requestMatchers("/booking/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);

        return  security.build();
    }



}
