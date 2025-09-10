package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.City;
import com.cfs.bookMyShow.model.type.Address;
import lombok.Data;

@Data
public class TheaterDTO {
    private Long id;
    private String name;
    private City cityName;
    private Address address;
    private  Long totalScreens;
}
