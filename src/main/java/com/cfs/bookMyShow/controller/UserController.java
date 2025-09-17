package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.controller.types.ApiResponse;
import com.cfs.bookMyShow.dto.UserDTO;
import com.cfs.bookMyShow.dto.UserProfile;
import com.cfs.bookMyShow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody  UserDTO user){
            UserProfile profile =  userService.createUser(user);
            ApiResponse response =  new ApiResponse<>(
                    LocalDateTime.now(),
                    HttpStatus.CREATED,
                    profile
            );
            return   new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<UserProfile>> createUser(){
        List<UserProfile> userProfiles = userService.allUsers();
        ApiResponse response =  new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED,
                userProfiles
        );
        return   new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
