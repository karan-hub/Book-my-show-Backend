package com.cfs.bookMyShow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "title")
    private  String  title;

    @Column(name = "language" ,  nullable = false)
    private String language;

    @Column(name = "genre" , nullable = false)
    private  String genre;

    @OneToMany(
            mappedBy = "movie" ,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Show> shows = new ArrayList<>();

}
