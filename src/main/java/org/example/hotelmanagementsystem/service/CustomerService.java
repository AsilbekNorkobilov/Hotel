package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface CustomerService {
    HttpEntity<?> getMyOrders(Pageable pageable);

    HttpEntity<?> getMe();

    HttpEntity<?> edit(RegisterDto customerDto, UUID id);
}
