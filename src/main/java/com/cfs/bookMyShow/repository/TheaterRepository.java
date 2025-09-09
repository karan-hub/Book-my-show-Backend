package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByCityName(String cityName);
    List<Theater> findByAddress_PinCode(String postalCode);
}