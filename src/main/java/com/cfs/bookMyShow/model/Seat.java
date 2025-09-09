package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "screen_id",
            nullable = false
    )
    private  Screen screen;

    @Column(nullable = false)
    private  String  seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column(nullable = false)
    private Double basePrice;

}
