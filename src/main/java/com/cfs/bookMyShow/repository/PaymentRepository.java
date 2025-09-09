package com.cfs.bookMyShow.repository;

import com.cfs.bookMyShow.model.Payment;
import com.cfs.bookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTransactionId(String transactionId);

}