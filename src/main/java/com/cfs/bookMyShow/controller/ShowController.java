package com.cfs.bookMyShow.controller;

import com.cfs.bookMyShow.dto.ShowDTO;
import com.cfs.bookMyShow.service.ShowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

     @PostMapping("/add")
    public ResponseEntity<ShowDTO> addShow(@RequestBody ShowDTO showDTO) {
        ShowDTO savedShow = showService.addShow(showDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShow);
    }

     @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowDTO>> getShowsByMovie(@PathVariable Long movieId) {
        List<ShowDTO> shows = showService.getShowsByMovie(movieId);
        return ResponseEntity.ok(shows);
    }

     @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<ShowDTO>> getShowsByScreen(@PathVariable Long screenId) {
        List<ShowDTO> shows = showService.getShowsByScreen(screenId);
        return ResponseEntity.ok(shows);
    }

     @GetMapping("/between")
    public ResponseEntity<List<ShowDTO>> getShowsBetween(
            @RequestParam("start") @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME
            ) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME
            ) LocalDateTime end
    ) {
        List<ShowDTO> shows = showService.getShowsBetween(start, end);
        return ResponseEntity.ok(shows);
    }
}
