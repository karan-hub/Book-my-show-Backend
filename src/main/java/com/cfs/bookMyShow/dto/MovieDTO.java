package com.cfs.bookMyShow.dto;

import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private int duration;  // in minutes
    private String language;
    private String genre;
}
