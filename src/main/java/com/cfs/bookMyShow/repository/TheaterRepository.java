package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}