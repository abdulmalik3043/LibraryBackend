package com.nabdu.library.dao;

import com.nabdu.library.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByUserEmail(String userEmail);
}
