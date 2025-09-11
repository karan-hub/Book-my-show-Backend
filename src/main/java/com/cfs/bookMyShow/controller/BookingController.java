package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.dto.BookingDTO;
import com.cfs.bookMyShow.dto.RequestBookingDTO;
import com.cfs.bookMyShow.exception.ApiErrorResponse;
import com.cfs.bookMyShow.model.Booking;
import com.cfs.bookMyShow.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/api/booking")
@RestController
@RequiredArgsConstructor
public class BookingController {

    private  final BookingService bookingService;

    private <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, T body) {
        ApiResponse<T> response = new ApiResponse<>(
                LocalDateTime.now(),
                status,
                body
        );
        return ResponseEntity.status(status).body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BookingDTO>> createBooking(@RequestBody RequestBookingDTO dto){
        BookingDTO booking = bookingService.createBooking(dto);
        return   buildResponse(HttpStatus.CREATED, booking);
    }

}
