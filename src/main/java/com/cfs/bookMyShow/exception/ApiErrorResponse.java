package com.cfs.bookMyShow.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class ApiErrorResponse {
    private  int status ;
    private String message ; //---
    private  String  path ;
    private LocalDateTime  localDateTime;

}
