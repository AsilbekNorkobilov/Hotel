package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.example.hotelmanagementsystem.service.RoomService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/room")
@RequiredArgsConstructor
public class RoomController {
     private final RoomService roomService;
    @GetMapping("all")
    public HttpEntity<?> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("{type}")
    public HttpEntity<?> getRoomByType(@PathVariable RoomType type){
        return roomService.getRoomByType(type);
    }
    @GetMapping("archived")
    public HttpEntity<?> getAllArchivedRooms(){
        return roomService.getAllArchivedRooms();
    }
    @PatchMapping("restore/{id}")
    public HttpEntity<?> restoreArchivedRoom(@PathVariable UUID id){
        return roomService.restoreArchivedRoom(id);
    }

    @PostMapping()
    public HttpEntity<?> saveRoom(@RequestBody RoomReqDto roomReqDto){
        return roomService.saveRoom(roomReqDto);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRoom(@PathVariable UUID id){
        return roomService.deleteRoom(id);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editRoom(@RequestBody RoomReqDto roomReqDto, @PathVariable UUID id){
        return roomService.editRoom(id,roomReqDto);
    }
}
