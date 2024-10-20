package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.PaymentReqDto;
import org.example.hotelmanagementsystem.service.PaymentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping()
    public HttpEntity<?> createPayment(@RequestBody PaymentReqDto paymentReqDto) {
        return paymentService.savePayment(paymentReqDto);
    }

    @GetMapping()
    public HttpEntity<?> getAllPayments(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "payedAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return paymentService.getAllPayments(pageable);
    }

    @GetMapping("myPayments")
    public HttpEntity<?> getCustomersPayments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "payedAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return paymentService.getCustomersPayment(pageable);
    }

}
