package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}