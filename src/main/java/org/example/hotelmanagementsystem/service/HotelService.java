package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.model.reqDto.HotelReqDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface HotelService {
    HttpEntity<?> getAllHotels(Pageable pageable);

    HttpEntity<?> save(HotelReqDto hotelReqDto);

    HttpEntity<?> getAllArchivedHotels(Pageable pageable);

    HttpEntity<?> restoreArchivedHotel(UUID id);

    HttpEntity<?> deleteHotel(UUID id);

    HttpEntity<?> editHotel(UUID id, HotelReqDto hotelReqDto);
}
