package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}