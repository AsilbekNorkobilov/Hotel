package org.example.hotelmanagementsystem.model.resDto;

import lombok.Value;
import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.entity.enums.RoomType;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Room}
 */
@Value
public class RoomResDto implements Serializable {
    UUID id;
    String hotelName;
    Integer floor;
    Integer bedsCount;
    Integer roomNumber;
    RoomType roomType;
    Double price;
    Boolean isArchived;
}