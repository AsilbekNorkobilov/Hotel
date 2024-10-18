package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    List<Hotel> findAllByIsArchivedFalse();

    List<Hotel> findAllByIsArchivedTrue();
}