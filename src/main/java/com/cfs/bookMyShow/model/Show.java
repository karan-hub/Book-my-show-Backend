package com.cfs.bookMyShow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shows")
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(
            name = "movie_id",
            nullable = false
    )
    private Movie movie;

    @ManyToOne
    @JoinColumn(
            name = "screen_id"
    )
    private  Screen screen;

    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL
    )
    private List<Booking> bookings;

    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL
    )
    private List<ShowSeat> showSeats;



}
