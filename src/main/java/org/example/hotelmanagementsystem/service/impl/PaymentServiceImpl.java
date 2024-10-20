package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.component.CurrentUser;
import org.example.hotelmanagementsystem.entity.Payment;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.mappers.PaymentMapper;
import org.example.hotelmanagementsystem.model.reqDto.PaymentReqDto;
import org.example.hotelmanagementsystem.model.resDto.PaymentResDto;
import org.example.hotelmanagementsystem.repo.PaymentRepository;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.example.hotelmanagementsystem.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CurrentUser currentUser;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    @Override
    public HttpEntity<?> savePayment(PaymentReqDto paymentReqDto) {
        Payment entity = paymentMapper.toEntity(paymentReqDto);
        paymentRepository.save(entity);
        return ResponseEntity.ok("Payment saved");
    }

    @Override
    public HttpEntity<?> getAllPayments(Pageable pageable) {
        List<Payment> all = paymentRepository.findAll();
        List<PaymentResDto> paymentResList = all.stream().map(paymentMapper::toDto).collect(Collectors.toList());
        Page<PaymentResDto> pagedPayments=new PageImpl<>(paymentResList,pageable,paymentResList.size());
        return ResponseEntity.ok(pagedPayments);
    }
    @Override
    public HttpEntity<?> getCustomersPayment(Pageable pageable) {
        String email = currentUser.getMe();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        List<Payment> customersPayment = paymentRepository.findCustomersPayment(user.getId());
        List<PaymentResDto> paymentResList = customersPayment.stream().map(paymentMapper::toDto).collect(Collectors.toList());
        Page<PaymentResDto> pagedPayments=new PageImpl<>(paymentResList,pageable,paymentResList.size());
        return ResponseEntity.ok(pagedPayments);
    }
}
