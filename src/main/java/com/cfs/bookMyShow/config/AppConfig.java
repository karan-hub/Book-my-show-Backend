package com.cfs.bookMyShow.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

        @Bean
        public ModelMapper mapper(){
                     return  new ModelMapper();
        }

        @Bean
        public PasswordEncoder encoder(){
            return  new BCryptPasswordEncoder();
        }

//    @Bean
    public UserDetailsService userDetailsService (){
        UserDetails karan= User.withUsername("karan")
                .password(encoder().encode("pass"))
                .roles("ADMIN")
                .build();

        UserDetails kishor= User.withUsername("kishor")
                .password(encoder().encode("pass"))
                .roles("ADMIN")
                .build();

        UserDetails rohan= User.withUsername("rohan")
                .password(encoder().encode("pass"))
                .roles("USER")
                .build();

        return  new InMemoryUserDetailsManager(karan , rohan, kishor);
    }

}

