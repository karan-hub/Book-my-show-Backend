package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class ShowSeatDTO {
    private Long id;
    private boolean booked;
    private   SeatDTO seat;
}
