package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.example.hotelmanagementsystem.service.RoomService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/room")
@RequiredArgsConstructor
public class RoomController {
     private final RoomService roomService;
    @GetMapping("all")
    public HttpEntity<?> getAllRooms(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getAllRooms(pageable);
    }

    @GetMapping("{type}")
    public HttpEntity<?> getRoomByType(@PathVariable RoomType type,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getRoomByType(type,pageable);
    }
    @GetMapping("archived")
    public HttpEntity<?> getAllArchivedRooms(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getAllArchivedRooms(pageable);
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
