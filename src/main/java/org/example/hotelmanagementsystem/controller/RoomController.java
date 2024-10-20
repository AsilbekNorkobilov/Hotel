package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.example.hotelmanagementsystem.service.RoomService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("type")
    public HttpEntity<?> getRoomByType(@RequestParam RoomType type,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getRoomByType(type,pageable);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("archived")
    public HttpEntity<?> getAllArchivedRooms(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getAllArchivedRooms(pageable);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("restore/{id}")
    public HttpEntity<?> restoreArchivedRoom(@PathVariable UUID id){
        return roomService.restoreArchivedRoom(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public HttpEntity<?> saveRoom(@RequestBody RoomReqDto roomReqDto){
        return roomService.saveRoom(roomReqDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRoom(@PathVariable UUID id){
        return roomService.deleteRoom(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public HttpEntity<?> editRoom(@RequestBody RoomReqDto roomReqDto, @PathVariable UUID id){
        return roomService.editRoom(id,roomReqDto);
    }
}
