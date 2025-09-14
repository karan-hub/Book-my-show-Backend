package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.dto.ShowDTO;
import com.cfs.bookMyShow.exception.NotFoundException;
import com.cfs.bookMyShow.model.Movie;
import com.cfs.bookMyShow.model.Screen;
import com.cfs.bookMyShow.model.Show;
import com.cfs.bookMyShow.repository.MovieRepository;
import com.cfs.bookMyShow.repository.ScreenRepository;
import com.cfs.bookMyShow.repository.ShowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    @Transactional
    public ShowDTO addShow(ShowDTO dto) {

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        Screen screen = screenRepository.findById(dto.getScreenId())
                .orElseThrow(() -> new NotFoundException("Screen not found"));


        Show show = new Show();
        show.setStartTime(dto.getStartTime());
        show.setEndTime(dto.getEndTime());
        show.setMovie(movie);
        show.setScreen(screen);


        show = showRepository.save(show);

        return mapToDto(show);
    }

    public List<ShowDTO> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ShowDTO> getShowsByScreen(Long screenId) {
        return showRepository.findByScreenId(screenId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ShowDTO> getShowsBetween(LocalDateTime start, LocalDateTime end) {
        return showRepository.findByStartTimeBetween(start, end).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ShowDTO mapToDto(Show show) {
        ShowDTO dto = new ShowDTO();
        dto.setId(show.getId());
        dto.setStartTime(show.getStartTime());
        dto.setEndTime(show.getEndTime());
        dto.setMovieId(show.getMovie().getId());
        dto.setMovieName(show.getMovie().getTitle());
        dto.setScreenId(show.getScreen().getId());
        dto.setScreenName(show.getScreen().getName());
        return dto;
    }
}
