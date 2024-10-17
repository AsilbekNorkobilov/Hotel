package org.example.hotelmanagementsystem.mappers;

import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.model.reqDto.RoomReqDto;
import org.example.hotelmanagementsystem.model.resDto.RoomResDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    @Mapping(source = "hotelId", target = "hotel.id")
    Room toEntity(RoomReqDto roomReqDto);

    @Mapping(source = "hotel.name", target = "hotelName")
    RoomResDto toDto(Room room);

}