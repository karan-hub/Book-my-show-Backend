package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
//    Optional<User> findByPhoneNumber(String phoneNumber);
}