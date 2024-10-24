package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.model.reqDto.AvailableRoomReqDto;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface RoomService {
    HttpEntity<?> getAllRooms(Pageable pageable);

    HttpEntity<?> getRoomByType(RoomType type, Pageable pageable);

    HttpEntity<?> saveRoom(RoomReqDto roomReqDto);

    HttpEntity<?> deleteRoom(UUID id);

    HttpEntity<?> getAllArchivedRooms(Pageable pageable);

    HttpEntity<?> restoreArchivedRoom(UUID id);

    HttpEntity<?> editRoom(UUID id, RoomReqDto roomReqDto);

    HttpEntity<?> getAvailableRooms(AvailableRoomReqDto availableRoomReqDto, Pageable pageable);
}
