package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(
            name = "name" ,
            nullable = false
    )
    private  String name;

    @Column(unique = true)
    private  String  username;

    @Column(unique = true)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(
            name = "email" ,
            nullable = false
    )
    private String email;

    @Column(
            name =  "role" ,
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(
            mappedBy="user" ,
            cascade = CascadeType.ALL
    )
    private List<Booking> bookings;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
