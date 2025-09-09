package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Booking;
import com.cfs.bookMyShow.model.Movie;
import com.cfs.bookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {



}