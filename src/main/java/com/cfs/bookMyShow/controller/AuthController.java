package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.dto.LoginRequestDto;
import com.cfs.bookMyShow.dto.LoginResponseDto;
import com.cfs.bookMyShow.dto.SignupRequestDto;
import com.cfs.bookMyShow.dto.SignupResponseDto;
import com.cfs.bookMyShow.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService  authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        return  ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto request){
        return  ResponseEntity.ok(authService.signup(request));
//        return  ResponseEntity.ok("authService.signup(request)");
    }

}
