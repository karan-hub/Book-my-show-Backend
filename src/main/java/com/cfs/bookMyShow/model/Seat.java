package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "screen_id"
    )
    private  Screen screen;

    @Column(nullable = false)
    private  Long  seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;


    @ManyToMany(mappedBy = "seats")
    private List<Booking> bookings = new ArrayList<>();

}
