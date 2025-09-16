package com.cfs.bookMyShow.controller.types;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class Response {

    protected  <T> ResponseEntity<ApiResponse> buildResponse(HttpStatus status , T body){
        ApiResponse<T> response = new ApiResponse<>(
          LocalDateTime.now(),
          status,
          body      
        );
        return ResponseEntity.status(status).body(response);
    }
}
