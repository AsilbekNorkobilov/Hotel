package org.example.hotelmanagementsystem.mappers;

import org.example.hotelmanagementsystem.entity.Hotel;
import org.example.hotelmanagementsystem.model.reqDto.HotelReqDto;
import org.example.hotelmanagementsystem.model.resDto.HotelResDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HotelMapper {
    Hotel toEntity(HotelReqDto hotelReqDto);

    HotelResDto toDto(Hotel hotel);

}