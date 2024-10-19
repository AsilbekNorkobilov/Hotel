package org.example.hotelmanagementsystem.model.resDto;

import lombok.Value;
import org.example.hotelmanagementsystem.entity.enums.PayType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Payment}
 */
@Value
public class PaymentResDto implements Serializable {
    UUID id;
    UUID orderId;
    Double amount;
    PayType payType;
    LocalDateTime payedAt;
}