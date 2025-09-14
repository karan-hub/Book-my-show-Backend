package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.dto.TheaterDTO;
import com.cfs.bookMyShow.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theater")
public class TheaterController  extends  Response {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse>  addTheater(@RequestBody  TheaterDTO dto){
        TheaterDTO theater = theaterService.addTheater(dto);
        return  buildResponse(HttpStatus.ACCEPTED , theater);
    }


    @GetMapping
    public ResponseEntity<ApiResponse>  addTheater(@RequestParam String name){
        TheaterDTO theater = theaterService.findByName(name);
        return  buildResponse(HttpStatus.FOUND , theater);
    }


}
