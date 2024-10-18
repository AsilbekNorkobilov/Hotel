package org.example.hotelmanagementsystem.model.resDto;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Hotel}
 */
@Value
public class HotelResDto implements Serializable {
    UUID id;
    String name;
    String phone;
    String email;
    String address;
    Boolean isArchived;

}