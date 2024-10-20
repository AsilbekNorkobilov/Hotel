package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.mappers.RoomMapper;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.example.hotelmanagementsystem.model.resDto.RoomResDto;
import org.example.hotelmanagementsystem.repo.RoomRepository;
import org.example.hotelmanagementsystem.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public HttpEntity<?> getAllRooms(Pageable pageable) {
        List<Room> allRooms = roomRepository.findAllByArchivedFalse();
        List<RoomResDto> roomsResList = allRooms.stream().map(roomMapper::toDto).collect(Collectors.toList());
        Page<RoomResDto> pagedRooms=new PageImpl<>(roomsResList,pageable,roomsResList.size());
        return ResponseEntity.ok(pagedRooms);
    }

    @Override
    public HttpEntity<?> getRoomByType(RoomType type, Pageable pageable) {
        List<Room> allByRoomType = roomRepository.findAllByRoomTypeAndArchivedFalse(type);
        List<RoomResDto> roomsResList = allByRoomType.stream().map(roomMapper::toDto).collect(Collectors.toList());
        Page<RoomResDto> pagedRooms=new PageImpl<>(roomsResList,pageable,roomsResList.size());
        return ResponseEntity.ok(pagedRooms);
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
        room.setArchived(true);
        roomRepository.save(room);
        return ResponseEntity.ok("Room archived");
    }

    @Override
    public HttpEntity<?> getAllArchivedRooms(Pageable pageable) {
        List<Room> allByIsArchivedTrue = roomRepository.findAllByArchivedTrue();
        List<RoomResDto> roomsResList = allByIsArchivedTrue.stream().map(roomMapper::toDto).collect(Collectors.toList());
        Page<RoomResDto> pagedRooms=new PageImpl<>(roomsResList,pageable,roomsResList.size());
        return ResponseEntity.ok(pagedRooms);
    }

    @Override
    public HttpEntity<?> restoreArchivedRoom(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setArchived(false);
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
