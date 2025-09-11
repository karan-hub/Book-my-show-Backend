package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.Theater;
import com.cfs.bookMyShow.model.type.BookingStatus;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class BookingDTO {
    private String bookingNumber;
    private LocalDateTime bookingTime;
    private String status;
    private Double totalAmount;
    private PaymentDTO payment;
    private UserDTO user;
    private ShowDTO show;
    private MovieDTO movie;
    private List<ShowSeatDTO> seats;

}
