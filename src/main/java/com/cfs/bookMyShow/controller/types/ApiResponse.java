package com.cfs.bookMyShow.controller.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private LocalDateTime localDateTime;
    private HttpStatus status;
    private  T data;
}
