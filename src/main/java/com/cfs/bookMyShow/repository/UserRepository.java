package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);
//    Optional<User> findByPhoneNumber(String phoneNumber);
}