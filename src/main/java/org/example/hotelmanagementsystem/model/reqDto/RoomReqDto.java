package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;
import org.example.hotelmanagementsystem.entity.enums.RoomType;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Room}
 */
@Value
public class RoomReqDto implements Serializable {
    UUID hotelId;
    Integer floor;
    Integer bedsCount;
    Integer roomNumber;
    RoomType roomType;
    Double price;

}