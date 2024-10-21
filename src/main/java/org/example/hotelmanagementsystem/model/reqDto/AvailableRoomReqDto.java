package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Order}
 */
@Value
public class AvailableRoomReqDto implements Serializable {
    Integer roomBedsCount;
    LocalDate checkIn;
    LocalDate checkOut;
}