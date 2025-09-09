package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class SeatDTO {
    private Long id;
    private String seatNumber;
    private String seatType;  // NORMAL / PREMIUM / VIP
}
