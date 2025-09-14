package com.cfs.bookMyShow.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTO {
    private Long id;
    private Long movieId;
    private Long screenId;
    private LocalDateTime startTime;  // ISO String format: 2025-09-09T18:30
    private LocalDateTime endTime;
    private String movieName;
    private String screenName;
}
