package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.mappers.RoomMapper;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.example.hotelmanagementsystem.model.resDto.RoomResDto;
import org.example.hotelmanagementsystem.repo.RoomRepository;
import org.example.hotelmanagementsystem.service.RoomService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public HttpEntity<?> getAllRooms() {
        List<Room> allRooms = roomRepository.findAllByIsArchivedFalse();
        List<RoomResDto> roomsResList=new ArrayList<>();
        for (Room room : allRooms) {
            roomsResList.add(roomMapper.toDto(room));
        }
        return ResponseEntity.ok(roomsResList);
    }

    @Override
    public HttpEntity<?> getRoomByType(RoomType type) {
        List<Room> allByRoomType = roomRepository.findAllByRoomTypeAndIsArchivedFalse(type);
        List<RoomResDto> roomsResList=new ArrayList<>();
        for (Room room : allByRoomType) {
            roomsResList.add(roomMapper.toDto(room));
        }
        return ResponseEntity.ok(roomsResList);
    }

    @Override
    public HttpEntity<?> saveRoom(RoomReqDto roomReqDto) {
        Room room = roomMapper.toEntity(roomReqDto);
        roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room.getId());
    }

    @Override
    public HttpEntity<?> deleteRoom(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setIsArchived(true);
        roomRepository.save(room);
        return ResponseEntity.ok("Room archived");
    }

    @Override
    public HttpEntity<?> getAllArchivedRooms() {
        List<Room> allByIsArchivedTrue = roomRepository.findAllByIsArchivedTrue();
        List<RoomResDto> roomsResList=new ArrayList<>();
        for (Room room : allByIsArchivedTrue) {
            roomsResList.add(roomMapper.toDto(room));
        }
        return ResponseEntity.ok(roomsResList);
    }

    @Override
    public HttpEntity<?> restoreArchivedRoom(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setIsArchived(false);
        roomRepository.save(room);
        return ResponseEntity.ok("Room restored");
    }

    @Override
    public HttpEntity<?> editRoom(UUID id, RoomReqDto roomReqDto) {
        Room room = roomMapper.toEntity(roomReqDto);
        room.setId(id);
        roomRepository.save(room);
        return ResponseEntity.ok("Room edited");
    }
}
