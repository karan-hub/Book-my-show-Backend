package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class ShowSeatDTO {
    private Long id;
    private Long seatId;
    private Long showId;
    private boolean booked;
    private double price;
}
