package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CityRepository extends JpaRepository<City, Long> {
}