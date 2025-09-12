package com.cfs.bookMyShow.dto;

import com.cfs.bookMyShow.model.Theater;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityDTO {
    private Long id;
    private  String name;
    private List<Theater> theaters = new ArrayList<>();
}
