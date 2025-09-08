package com.cfs.bookMyShow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @ManyToOne
    @JoinColumn(
            name = "theater_id"
    )
    private  Theater theater;

    @OneToMany(
            mappedBy = "screen" ,
            orphanRemoval = true ,
            cascade = CascadeType.ALL
    )
    private List<Seat> seats  = new ArrayList<>();

    @OneToMany(
            mappedBy = "screen",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Show> shows = new ArrayList<>();
}
