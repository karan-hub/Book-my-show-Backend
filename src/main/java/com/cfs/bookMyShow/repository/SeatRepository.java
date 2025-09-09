package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}