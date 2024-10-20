package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.model.reqDto.OrderReqDto;
import org.example.hotelmanagementsystem.model.reqDto.RateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface OrderService {
    HttpEntity<?> getAllOrders(Pageable pageable);

    HttpEntity<?> makeOrder(OrderReqDto orderReqDto);

    HttpEntity<?> rateOrder(UUID id, RateDto rateDto);
}
