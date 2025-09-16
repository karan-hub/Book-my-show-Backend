package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.controller.types.ApiResponse;
import com.cfs.bookMyShow.controller.types.Response;
import com.cfs.bookMyShow.model.City;
import com.cfs.bookMyShow.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController extends Response {

    @Autowired
    private CityService  cityService ;


    @PostMapping("/new")
    public ResponseEntity<ApiResponse> creteCity(@RequestParam  String  name){
        City city =  cityService.addCity(name);
        return   buildResponse(HttpStatus.CREATED,city);
    }

}
