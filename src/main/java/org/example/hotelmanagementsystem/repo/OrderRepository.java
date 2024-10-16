package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}