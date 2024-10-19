package org.example.hotelmanagementsystem.mappers;

import org.example.hotelmanagementsystem.entity.Payment;
import org.example.hotelmanagementsystem.model.reqDto.PaymentReqDto;
import org.example.hotelmanagementsystem.model.resDto.PaymentResDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    @Mapping(source = "orderId", target = "order.id")
    Payment toEntity(PaymentReqDto paymentReqDto);

    @Mapping(source = "order.id", target = "orderId")
    PaymentResDto toDto(Payment payment);

}