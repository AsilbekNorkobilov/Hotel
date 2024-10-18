package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.Hotel;
import org.example.hotelmanagementsystem.mappers.HotelMapper;
import org.example.hotelmanagementsystem.model.reqDto.HotelReqDto;
import org.example.hotelmanagementsystem.model.resDto.HotelResDto;
import org.example.hotelmanagementsystem.repo.HotelRepository;
import org.example.hotelmanagementsystem.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    @Override
    public HttpEntity<?> getAllHotels(Pageable pageable) {
        List<Hotel> all = hotelRepository.findAllByIsArchivedFalse();
        List<HotelResDto> hotelResList=new ArrayList<>();
        for (Hotel hotel : all) {
            hotelResList.add(hotelMapper.toDto(hotel));
        }
        Page<HotelResDto> pagedHotel=new PageImpl<>(hotelResList,pageable,hotelResList.size());
        return ResponseEntity.ok(pagedHotel);
    }

    @Override
    public HttpEntity<?> save(HotelReqDto hotelReqDto) {
        Hotel entity = hotelMapper.toEntity(hotelReqDto);
        hotelRepository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel saved");
    }

    @Override
    public HttpEntity<?> getAllArchivedHotels(Pageable pageable) {
        List<Hotel> all = hotelRepository.findAllByIsArchivedTrue();
        List<HotelResDto> archivedHotels=new ArrayList<>();
        for (Hotel hotel : all) {
            archivedHotels.add(hotelMapper.toDto(hotel));
        }
        Page<HotelResDto> pagedHotel=new PageImpl<>(archivedHotels,pageable,archivedHotels.size());
        return ResponseEntity.ok(pagedHotel);
    }

    @Override
    public HttpEntity<?> restoreArchivedHotel(UUID id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setIsArchived(false);
        hotelRepository.save(hotel);
        return ResponseEntity.ok("Hotel restored");
    }

    @Override
    public HttpEntity<?> deleteHotel(UUID id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        hotel.setIsArchived(true);
        hotelRepository.save(hotel);
        return ResponseEntity.ok("Hotel deleted");
    }

    @Override
    public HttpEntity<?> editHotel(UUID id, HotelReqDto hotelReqDto) {
        Hotel entity = hotelMapper.toEntity(hotelReqDto);
        entity.setId(id);
        hotelRepository.save(entity);
        return ResponseEntity.ok("Hotel edited");
    }
}
