package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.Theater;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class BookingDTO {
    private UUID bookingNumber;
    private LocalDateTime bookingTime;
    private double totalAmount;
    private String status;
    private PaymentDTO payment;
    private UserDTO user;
    private ShowDTO show;
    private List<ShowSeatDTO> seats;

}
