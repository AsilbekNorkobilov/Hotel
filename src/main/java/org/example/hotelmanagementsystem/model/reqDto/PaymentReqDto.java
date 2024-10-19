package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Value;
import org.example.hotelmanagementsystem.entity.enums.PayType;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.example.hotelmanagementsystem.entity.Payment}
 */
@Value
public class PaymentReqDto implements Serializable {
    UUID orderId;
    Double amount;
    PayType payType;
}