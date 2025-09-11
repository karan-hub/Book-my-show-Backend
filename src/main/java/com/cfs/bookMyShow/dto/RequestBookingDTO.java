package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.controller.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookingDTO {
    private Long userId;
    protected Long showId;
    private List<Long> seatIds;
    private String paymentMethod;

}
