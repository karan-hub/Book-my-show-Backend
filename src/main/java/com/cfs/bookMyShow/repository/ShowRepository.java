package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}