package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
}