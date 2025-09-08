package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name" , nullable = false)
    private  String name;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name =  "role" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="user")
    private List<Booking> bookings;


}
