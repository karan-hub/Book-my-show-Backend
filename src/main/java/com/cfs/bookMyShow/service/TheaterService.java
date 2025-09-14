package com.cfs.bookMyShow.service;

import com.cfs.bookMyShow.dto.CityDTO;
import com.cfs.bookMyShow.dto.ScreenDTO;
import com.cfs.bookMyShow.dto.TheaterDTO;
import com.cfs.bookMyShow.exception.AlreadyExistException;
import com.cfs.bookMyShow.exception.NotFoundException;
import com.cfs.bookMyShow.model.City;
import com.cfs.bookMyShow.model.Screen;
import com.cfs.bookMyShow.model.Theater;
import com.cfs.bookMyShow.repository.CityRepository;
import com.cfs.bookMyShow.repository.TheaterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheaterService {

    @Autowired
    private  final TheaterRepository theaterRepository;

    @Autowired
    private  final CityRepository cityRepository;

    @Transactional
    public TheaterDTO addTheater(TheaterDTO dto) {

        City city = cityRepository.findByName(dto.getCity().getName());
        if (city == null) {
            throw new NotFoundException("Sorry, City not found");
        }

        boolean exists = city.getTheaters().stream()
                .anyMatch(theater -> theater.getName().equalsIgnoreCase(dto.getName()));
        if (exists) {
            throw new AlreadyExistException("In this city, Theater already exists");
        }


        Theater theater = new Theater();
        theater.setName(dto.getName());
        theater.setTotalScreens(dto.getTotalScreens());
        theater.setCity(city);


        if (dto.getScreens() != null) {
            Theater finalTheater = theater;
            List<Screen> screens = dto.getScreens().stream().map(screenDTO -> {
                Screen screen = new Screen();
                screen.setName(screenDTO.getName());
                screen.setTheater(finalTheater);
                return screen;
            }).collect(Collectors.toList());
            theater.setScreens(screens);
        }

         city.getTheaters().add(theater);

         theater = theaterRepository.save(theater);

         return mapToDto(theater);
    }


    public  TheaterDTO findByName(String name){
        Theater theater =  theaterRepository.findByName(name);
        return  mapToDto(theater);
    }

    private TheaterDTO mapToDto(Theater theater) {
        TheaterDTO dto = new TheaterDTO();

        dto.setId(theater.getId());
        dto.setName(theater.getName());
        dto.setTotalScreens(theater.getTotalScreens());

        // CityDTO
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(theater.getCity().getId());
        cityDTO.setName(theater.getCity().getName());
        dto.setCity(cityDTO);

        // ScreensDTO
        List<ScreenDTO> screenDTOs = theater.getScreens().stream().map(screen -> {
            ScreenDTO s = new ScreenDTO();
            s.setName(screen.getName());
            return s;
        }).collect(Collectors.toList());
        dto.setScreens(screenDTOs);

        return dto;
    }


}
