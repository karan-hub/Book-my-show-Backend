package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @OneToOne(mappedBy = "payment")
   private  Booking   booking ;

    @Column(nullable = false,unique = true)
    private String transactionId;

    @Column(nullable = false)
    private  Long    amount ;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status ;

    @CreationTimestamp
    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private String paymentMethod;

}
