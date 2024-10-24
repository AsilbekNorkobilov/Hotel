package org.example.hotelmanagementsystem.repo;

import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    List<Room> findAllByRoomTypeAndArchivedFalse(RoomType roomType);

    List<Room> findAllByArchivedFalse();

    List<Room> findAllByArchivedTrue();

    @Query(nativeQuery = true, value = "SELECT * FROM room WHERE beds_count =:bedsCount AND room_type =:roomType AND NOT is_archived AND id NOT IN (\n" +
            "    SELECT o.room_id FROM orders o WHERE NOT ( (o.check_out <=:checkIn) OR (o.check_in >=:checkOut)))")
    List<Room> allEmptyRooms(RoomType roomType, Integer bedsCount, LocalDate checkIn, LocalDate checkOut);

    @Query(nativeQuery = true, value = "SELECT * FROM room WHERE beds_count =:bedsCount AND NOT is_archived AND id NOT IN (\n" +
            "    SELECT o.room_id FROM orders o WHERE NOT ( (o.check_out <=:checkIn) OR (o.check_in >=:checkOut)))")
    List<Room> allAvailableRooms(Integer bedsCount, LocalDate checkIn, LocalDate checkOut);
}