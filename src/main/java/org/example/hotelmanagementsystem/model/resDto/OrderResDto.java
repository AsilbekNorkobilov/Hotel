package org.example.hotelmanagementsystem.model.resDto;

import lombok.Value;
import org.example.hotelmanagementsystem.entity.enums.RoomType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Order}
 */
@Value
public class OrderResDto implements Serializable {
    UUID id;
    Integer roomFloor;
    Integer roomBedsCount;
    Integer roomRoomNumber;
    RoomType roomRoomType;
    Double roomPrice;
    String userEmail;
    LocalDate checkIn;
    LocalDate checkOut;
    Integer rate;
    String comment;
    LocalDateTime createdAt;

}