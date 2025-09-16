package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.cfs.bookMyShow.model.type.Role.CUSTOMER;

@Data
@AllArgsConstructor
public class SignupRequestDto {

    private  String username;
    private  String name;
    private  String password;
    private String email;
    private String phoneNumber;

}
