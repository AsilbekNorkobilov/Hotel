package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.model.reqDto.DownloadOrderDto;
import org.example.hotelmanagementsystem.model.reqDto.OrderByRoomDto;
import org.example.hotelmanagementsystem.model.reqDto.OrderReqDto;
import org.example.hotelmanagementsystem.model.reqDto.RateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface OrderService {
    HttpEntity<?> getAllOrders(Pageable pageable);

    HttpEntity<?> makeOrderIfAvailable(OrderReqDto orderReqDto);

    HttpEntity<?> rateOrder(UUID id, RateDto rateDto);

    HttpEntity<?> downloadOrderFile(DownloadOrderDto downloadOrderDto);

    HttpEntity<?> makeOrderByRoomId(OrderByRoomDto orderByRoomDto);
}
