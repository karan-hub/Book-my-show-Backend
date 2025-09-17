package com.cfs.bookMyShow.security;

import com.cfs.bookMyShow.dto.LoginRequestDto;
import com.cfs.bookMyShow.dto.LoginResponseDto;
import com.cfs.bookMyShow.dto.SignupRequestDto;
import com.cfs.bookMyShow.dto.SignupResponseDto;
import com.cfs.bookMyShow.exception.NotFoundException;
import com.cfs.bookMyShow.model.User;
import com.cfs.bookMyShow.model.type.Role;
import com.cfs.bookMyShow.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

@RequiredArgsConstructor
@Service
public class AuthService {


    private  final AuthenticationManager authenticationManager;
    private  final AuthUtil authUtil;

    private final UserRepository userRepository ;

    private final  PasswordEncoder encoder;

    public  LoginResponseDto login(LoginRequestDto request) {
        Authentication  authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword())
        );
        User  user = (User) authentication.getPrincipal();
        String  token =authUtil.generateAccessToken(user);

        return  new LoginResponseDto(token, user.getId().toString() , user.getUsername());
    }


    public SignupResponseDto signup(SignupRequestDto request) {
        User  user =  userRepository.findByUsername(request.getUsername()).orElse(null);
        if ( user != null)  throw  new NotFoundException("Invalid Credential ");

        user = userRepository.save(
                new User().builder()
                        .name(request.getName())
                        .username(request.getUsername())
                        .password(encoder.encode(request.getPassword()))
                        .phoneNumber(request.getPhoneNumber())
                        .email(request.getEmail())
                        .role(Role.CUSTOMER)
                        .build()
        );

      return  new SignupResponseDto(user.getUsername() , user.getPassword());


    }



}














