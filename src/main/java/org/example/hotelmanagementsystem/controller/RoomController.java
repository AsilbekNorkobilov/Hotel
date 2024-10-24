package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.model.reqDto.AvailableRoomReqDto;
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

    @Operation(summary = "All rooms",description = "Endpoint returns all rooms")
    @GetMapping("all")
    public HttpEntity<?> getAllRooms(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getAllRooms(pageable);
    }
    @Operation(summary = "All rooms by type",description = "Endpoint returns all rooms by provided type")
    @GetMapping("type")
    public HttpEntity<?> getRoomByType(@RequestParam RoomType type,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getRoomByType(type,pageable);
    }

    @Operation(summary = "All rooms by type",description = "Endpoint returns all rooms by provided type")
    @GetMapping("available")
    public HttpEntity<?> getAvailableRooms(@RequestBody AvailableRoomReqDto availableRoomReqDto,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getAvailableRooms(availableRoomReqDto,pageable);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "All archived rooms",description = "Endpoint returns all archived rooms. Accessible only to users with the 'ROLE_ADMIN' role")
    @GetMapping("archived")
    public HttpEntity<?> getAllArchivedRooms(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return roomService.getAllArchivedRooms(pageable);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Restore archived room",description = "Endpoint will restore archived room by id. Accessible only to users with the 'ROLE_ADMIN' role")
    @PatchMapping("restore/{id}")
    public HttpEntity<?> restoreArchivedRoom(@PathVariable UUID id){
        return roomService.restoreArchivedRoom(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save a new room",description = "Save a new room with the provided details. Accessible only to users with the 'ROLE_ADMIN' role")
    @PostMapping
    public HttpEntity<?> saveRoom(@RequestBody RoomReqDto roomReqDto){
        return roomService.saveRoom(roomReqDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete room",description = "Endpoint will archive room by id. Accessible only to users with the 'ROLE_ADMIN' role")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRoom(@PathVariable UUID id){
        return roomService.deleteRoom(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a room by its ID",description = "Updates the details of an existing room identified by the provided ID. Accessible only to users with the 'ROLE_ADMIN' role")
    @PutMapping("{id}")
    public HttpEntity<?> editRoom(@RequestBody RoomReqDto roomReqDto, @PathVariable UUID id){
        return roomService.editRoom(id,roomReqDto);
    }
}
