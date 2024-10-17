package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface RoomService {
    HttpEntity<?> getAllRooms();

    HttpEntity<?> getRoomByType(RoomType type);

    HttpEntity<?> saveRoom(RoomReqDto roomReqDto);

    HttpEntity<?> deleteRoom(UUID id);

    HttpEntity<?> getAllArchivedRooms();

    HttpEntity<?> restoreArchivedRoom(UUID id);

    HttpEntity<?> editRoom(UUID id, RoomReqDto roomReqDto);
}
