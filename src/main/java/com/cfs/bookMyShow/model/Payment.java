package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "booking_id"
    )
   private  Booking   booking ;

    @Column(nullable = false)
    private  Long    amount ;

    @Column(nullable = false)
    private Status status ;

    @CreationTimestamp
    private LocalDateTime paymentTime;

}
