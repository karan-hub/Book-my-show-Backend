package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class UserProfile {
    private Long id;
    private String name;
    private String email;
    private String number;
    private  String role;
}
