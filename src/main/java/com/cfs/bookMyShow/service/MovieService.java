package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.dto.MovieDTO;
import com.cfs.bookMyShow.model.Movie;
import com.cfs.bookMyShow.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    @Autowired
    private  final MovieRepository  movieRepository ;

        public List<MovieDTO> findByLanguage(String  language){
            List<Movie> movie =  movieRepository.findByLanguage(language);
            return  movie.stream().map(this::mapToDto).toList();
        }

    public List<MovieDTO> findByGenre(String  language){
        List<Movie> movie =  movieRepository.findByLanguage(language);
        return  movie.stream().map(this::mapToDto).toList();
    }


    public List<MovieDTO> findByTitleContainingIgnoreCase(String  language){
        List<Movie> movie =  movieRepository.findByLanguage(language);
        return  movie.stream().map(this::mapToDto).toList();
    }

    private MovieDTO mapToDto(Movie movie) {
            MovieDTO dto = new MovieDTO();
            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setDuration(movie.getDuration());
            dto.setGenre(movie.getGenre());
            dto.setPosterUrl(movie.getPosterUrl());
            return  dto;
    }

}
