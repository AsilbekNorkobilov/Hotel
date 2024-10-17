package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    List<Room> findAllByRoomTypeAndIsArchivedFalse(RoomType roomType);

    List<Room> findAllByIsArchivedFalse();

    List<Room> findAllByIsArchivedTrue();
}