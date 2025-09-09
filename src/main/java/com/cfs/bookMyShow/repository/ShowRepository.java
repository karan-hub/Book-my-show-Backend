package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByStartTimeBetween(LocalDateTime startTime , LocalDateTime endTime );
    List<Show> findByMovieId(Long movieId);
    List<Show> findByScreenId(Long screenId);

}