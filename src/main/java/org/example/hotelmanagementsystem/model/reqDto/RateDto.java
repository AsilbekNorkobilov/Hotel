package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Order}
 */
@Value
public class RateDto implements Serializable {
    Integer rate;
    String comment;
}