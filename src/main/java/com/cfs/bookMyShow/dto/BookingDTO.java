package com.cfs.bookMyShow.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
    private double totalAmount;
    private String status;  // BOOKED / CANCELLED
}
