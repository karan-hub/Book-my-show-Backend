package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class ScreenDTO {
    private Long id;
    private String name;
    private Long theaterId;  // ya TheaterDTO agar nested bhejna hai
}
