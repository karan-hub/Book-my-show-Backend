package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.SeatType;
import com.cfs.bookMyShow.model.type.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private Double totalAmount;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL
    )
    private List<ShowSeat> showSeats;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment payment;


    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private  User user;

    @ManyToOne
    @JoinColumn(
            name = "show_id",
            nullable = false
    )
    private  Show show;

}
