package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByShowId(Long id);
    List<Booking> findByPaymentId(Long id);

}