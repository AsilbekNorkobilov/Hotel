package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Hotel}
 */
@Value
public class HotelReqDto implements Serializable {
    String name;
    String phone;
    String email;
    String address;
    Boolean isArchived;

}