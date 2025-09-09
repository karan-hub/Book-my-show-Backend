package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}