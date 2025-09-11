package com.cfs.bookMyShow.exception;


import com.cfs.bookMyShow.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler     {

    private final UserRepository userRepository;

    public GlobalExceptionHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public  ResponseEntity<ApiErrorResponse> seatNotAvailableHandler(SeatNotAvailableException exception , WebRequest request){
        ApiErrorResponse response =  new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        System.out.println("\n\n\n\n\nSeatNotAvailableException \n\n\n\n");
        return  new ResponseEntity<>( response , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<ApiErrorResponse> userNotFoundHandler(UserNotFoundException e ,  WebRequest request){
        ApiErrorResponse  response = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return  new   ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> globalExceptionalHandler(Exception e , WebRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    return  new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
