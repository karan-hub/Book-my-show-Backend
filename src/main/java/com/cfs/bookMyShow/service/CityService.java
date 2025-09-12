package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.controller.ApiResponse;
import com.cfs.bookMyShow.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<ApiResponse> addCity(){

    }


}
