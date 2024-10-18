package org.example.hotelmanagementsystem.mappers;

import org.example.hotelmanagementsystem.entity.Order;
import org.example.hotelmanagementsystem.model.resDto.OrderResDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(source = "userLastName", target = "user.lastName")
    @Mapping(source = "userFirstName", target = "user.firstName")
    @Mapping(source = "userEmail", target = "user.email")
    @Mapping(source = "roomPrice", target = "room.price")
    @Mapping(source = "roomRoomType", target = "room.roomType")
    @Mapping(source = "roomRoomNumber", target = "room.roomNumber")
    @Mapping(source = "roomBedsCount", target = "room.bedsCount")
    @Mapping(source = "roomFloor", target = "room.floor")
    Order toEntity(OrderResDto orderResDto);

    @InheritInverseConfiguration(name = "toEntity")
    OrderResDto toDto(Order order);

}