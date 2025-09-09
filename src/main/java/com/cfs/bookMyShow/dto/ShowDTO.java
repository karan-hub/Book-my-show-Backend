package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class ShowDTO {
    private Long id;
    private Long movieId;
    private Long screenId;
    private String startTime;  // ISO String format: 2025-09-09T18:30
    private String endTime;
}
