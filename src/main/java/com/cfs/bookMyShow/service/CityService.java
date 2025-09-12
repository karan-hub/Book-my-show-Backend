package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.controller.ApiResponse;
import com.cfs.bookMyShow.model.City;
import com.cfs.bookMyShow.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City  addCity(String  name){
        City city = new City();
        city.setName(name);
        City newCity = cityRepository.save(city);
        return    newCity;
    }
}
