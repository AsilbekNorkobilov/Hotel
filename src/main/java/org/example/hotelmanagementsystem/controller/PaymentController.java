package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.PaymentReqDto;
import org.example.hotelmanagementsystem.service.PaymentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Creating new Payment",description = "Save a new payment with the provided details")
    @PostMapping()
    public HttpEntity<?> createPayment(@RequestBody PaymentReqDto paymentReqDto) {
        return paymentService.savePayment(paymentReqDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "All payments",description = "Endpoint returns all payments")
    @GetMapping()
    public HttpEntity<?> getAllPayments(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "payedAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return paymentService.getAllPayments(pageable);
    }

    @Operation(summary = "All customers payments",description = "Endpoint returns all current customers payments")
    @GetMapping("myPayments")
    public HttpEntity<?> getCustomersPayments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "payedAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return paymentService.getCustomersPayment(pageable);
    }

}
