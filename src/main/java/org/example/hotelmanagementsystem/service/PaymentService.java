package org.example.hotelmanagementsystem.service;

import org.example.hotelmanagementsystem.model.reqDto.PaymentReqDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;

public interface PaymentService {
    HttpEntity<?> savePayment(PaymentReqDto paymentReqDto);

    HttpEntity<?> getAllPayments(Pageable pageable);

    HttpEntity<?> getCustomersPayment(Pageable pageable);
}
