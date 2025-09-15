package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.dto.BookingDTO;
import com.cfs.bookMyShow.dto.RequestBookingDTO;
import com.cfs.bookMyShow.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/numbers")
    public ResponseEntity<ApiResponse<List<String>>> getAllBookingNumbers() {
        List<String> bookingNumbers = bookingService.getAllBookingNumbers();
        return buildResponse(HttpStatus.FOUND , bookingNumbers);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BookingDTO>> createBooking(@RequestBody RequestBookingDTO dto){
        BookingDTO booking = bookingService.createBooking(dto);
        return   buildResponse(HttpStatus.CREATED, booking);
    }

}
