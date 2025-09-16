package com.cfs.bookMyShow.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity  security) throws  Exception{
        security.authorizeHttpRequests(
                outh->outh
                        .requestMatchers("/movie/**").permitAll()
//                        .requestMatchers("/booking/**").authenticated()
                        .requestMatchers("/booking/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
        ).formLogin(Customizer.withDefaults());

        return  security.build();
    }



}
