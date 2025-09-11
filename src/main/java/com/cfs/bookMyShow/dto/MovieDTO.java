package com.cfs.bookMyShow.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private LocalTime duration;
    private String language;
    private String genre;
    private String posterUrl;

}
