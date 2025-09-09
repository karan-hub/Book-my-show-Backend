package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShowId(Long movieId);
    List<ShowSeat> findByShowIdAndStatus(Long showId, String status);
}