package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

    List<Screen> findByTheaterId(Long theaterId);
    long countByTheaterId(Long theaterId);


}