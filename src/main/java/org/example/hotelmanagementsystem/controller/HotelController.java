package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.HotelReqDto;
import org.example.hotelmanagementsystem.service.HotelService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/hotel")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("all")
    public HttpEntity<?> getAllHotels(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return hotelService.getAllHotels(pageable);
    }

    @PostMapping()
    public HttpEntity<?> saveHotel(@RequestBody HotelReqDto hotelReqDto){
        return hotelService.save(hotelReqDto);
    }
    @GetMapping("archived")
    public HttpEntity<?> getAllArchivedHotel(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return hotelService.getAllArchivedHotels(pageable);
    }
    @PatchMapping("restore/{id}")
    public HttpEntity<?> restoreArchivedHotel(@PathVariable UUID id){
        return hotelService.restoreArchivedHotel(id);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteHotel(@PathVariable UUID id){
        return hotelService.deleteHotel(id);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editHotel(@RequestBody HotelReqDto hotelReqDto, @PathVariable UUID id){
        return hotelService.editHotel(id,hotelReqDto);
    }
}
