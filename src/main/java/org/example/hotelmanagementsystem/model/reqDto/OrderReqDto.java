package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;
import org.example.hotelmanagementsystem.entity.enums.RoomType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Order}
 */
@Value
public class OrderReqDto implements Serializable {
    Integer roomBedsCount;
    RoomType roomRoomType;
    UUID userId;
    LocalDate checkIn;
    LocalDate checkOut;
}