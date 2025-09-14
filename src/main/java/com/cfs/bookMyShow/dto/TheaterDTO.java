package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.City;
import com.cfs.bookMyShow.model.Screen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TheaterDTO {
    private Long id;
    private String name;
    private CityDTO city;
    private  Long totalScreens;
    private List<ScreenDTO> Screens;

}
