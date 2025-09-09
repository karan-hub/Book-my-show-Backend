package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}