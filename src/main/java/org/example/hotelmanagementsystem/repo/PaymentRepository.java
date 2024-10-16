package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}