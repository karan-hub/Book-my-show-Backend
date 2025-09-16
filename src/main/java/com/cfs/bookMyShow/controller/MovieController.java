package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.controller.types.ApiResponse;
import com.cfs.bookMyShow.controller.types.Response;
import com.cfs.bookMyShow.dto.MovieDTO;
import com.cfs.bookMyShow.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController extends Response {

    @Autowired
    private  final MovieService  service;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllMovies(){
        List<MovieDTO> movies= service.getAllMovie();
        return buildResponse(HttpStatus.FOUND , movies);
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMovie(@RequestBody MovieDTO dto) {
        MovieDTO response = service.addMovie(dto);
        return buildResponse(HttpStatus.OK, response); // 200 OK
    }

    @GetMapping("/by-language")
    public ResponseEntity<ApiResponse> findByLanguage(@RequestParam String language) {
        List<MovieDTO> response = service.findByLanguage(language);
        return buildResponse(HttpStatus.OK, response);
    }

    @GetMapping("/by-genre")
    public ResponseEntity<ApiResponse> findByGenre(@RequestParam String genre) {
        List<MovieDTO> response = service.findByGenre(genre);
        return buildResponse(HttpStatus.OK, response);
    }

    @GetMapping("/by-title")
    public ResponseEntity<ApiResponse> findByTitleContainingIgnoreCase(@RequestParam String title) {
        List<MovieDTO> response = service.findByTitleContainingIgnoreCase(title);
        return buildResponse(HttpStatus.OK, response);
    }

}
