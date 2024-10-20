package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.HotelReqDto;
import org.example.hotelmanagementsystem.service.HotelService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/hotel")
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "All hotels",description = "Endpoint returns all hotels")
    @GetMapping("all")
    public HttpEntity<?> getAllHotels(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return hotelService.getAllHotels(pageable);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save a new hotel",description = "Save a new hotel with the provided details. Accessible only to users with the 'ROLE_ADMIN' role")
    @PostMapping()
    public HttpEntity<?> saveHotel(@RequestBody HotelReqDto hotelReqDto){
        return hotelService.save(hotelReqDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "All archived hotels",description = "Endpoint returns all archived hotels. Accessible only to users with the 'ROLE_ADMIN' role")
    @GetMapping("archived")
    public HttpEntity<?> getAllArchivedHotel(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return hotelService.getAllArchivedHotels(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Restore archived hotel",description = "Endpoint will restore archived hotel by id. Accessible only to users with the 'ROLE_ADMIN' role")
    @PatchMapping("restore/{id}")
    public HttpEntity<?> restoreArchivedHotel(@PathVariable UUID id){
        return hotelService.restoreArchivedHotel(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete hotel",description = "Endpoint will archive hotel by id. Accessible only to users with the 'ROLE_ADMIN' role")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteHotel(@PathVariable UUID id){
        return hotelService.deleteHotel(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a hotel by its ID",description = "Updates the details of an existing hotel identified by the provided ID. Accessible only to users with the 'ROLE_ADMIN' role")
    @PutMapping("{id}")
    public HttpEntity<?> editHotel(@RequestBody HotelReqDto hotelReqDto, @PathVariable UUID id){
        return hotelService.editHotel(id,hotelReqDto);
    }
}
