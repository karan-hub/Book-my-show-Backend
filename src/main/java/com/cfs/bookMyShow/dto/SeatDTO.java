package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.type.SeatType;
import lombok.Data;

@Data
public class SeatDTO {
    private Long id;
    private String seatNumber;
    private SeatType seatType;  // NORMAL / PREMIUM / VIP
    private Double  basePrice;
}
