package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.type.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentDTO {
    private String bookingId;
    private String transactionId;
    private  Double GST;
    private Double actualAmount;
    private Double finalAmount;
    private LocalDateTime paymentTime;
    private String paymentMethod;
    private BookingStatus status;
}
