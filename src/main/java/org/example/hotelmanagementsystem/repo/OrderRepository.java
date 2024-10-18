package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Order;
import org.example.hotelmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUser(User customer);
}