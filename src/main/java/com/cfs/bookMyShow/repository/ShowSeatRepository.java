package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.ShowSeat;
import com.cfs.bookMyShow.model.type.SeatStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShowId(Long movieId);
//    List<ShowSeat> findByShowIdAndStatus(Long showId, SeatStatus status);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findByShowIdAndStatus(Long showId, SeatStatus status);

    List<ShowSeat> findByShowIdAndSeatIdIn(Long showId, List<Long> seatIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from ShowSeat s where s.show.id = :showId and s.id in :seatIds")
    List<ShowSeat> findSeatsForUpdate(@Param("showId") Long showId, @Param("seatIds") List<Long> seatIds);

}