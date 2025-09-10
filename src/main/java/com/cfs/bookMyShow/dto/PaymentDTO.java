package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.type.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentDTO {
    private UUID bookingId;
    private String transactionId;
    private Double amount;
    private LocalDateTime paymentTime;
    private String paymentMethod;
    private BookingStatus status;
}
