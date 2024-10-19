package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query(nativeQuery = true, value = "SELECT p.* from payment p join orders o on p.order_id = o.id where o.user_id=:id")
    List<Payment> findCustomersPayment(UUID id);
}