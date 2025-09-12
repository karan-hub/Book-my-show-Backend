package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.model.City;
import com.cfs.bookMyShow.service.CityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/city")
public class CityController extends  Response {

    @Autowired
    private CityService  cityService ;


    @PostMapping("/new")
    public ResponseEntity<ApiResponse> creteCity(@RequestParam  String  name){
        City city =  cityService.addCity(name);
        return   buildResponse(HttpStatus.CREATED,city);
    }

}
