package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.BookingStatus;
import com.cfs.bookMyShow.model.type.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "show_seat")
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private SeatStatus status;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(
            name = "show_id",
            nullable = false
    )
    private Show show;

    @ManyToOne
    @JoinColumn(
            name = "seat_id",
            nullable = false
    )
    private Seat seat;

    @ManyToOne
    @JoinColumn(
            name = "booking_id"
    )
    private Booking booking;

    @Version
    private Integer version;

}