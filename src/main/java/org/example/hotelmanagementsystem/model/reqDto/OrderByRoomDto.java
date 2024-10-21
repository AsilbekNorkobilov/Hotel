package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Order}
 */
@Value
public class OrderByRoomDto implements Serializable {
    UUID roomId;
    UUID userId;
    LocalDate checkIn;
    LocalDate checkOut;
}