package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.component.CurrentUser;
import org.example.hotelmanagementsystem.entity.Order;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.mappers.CustomerMapper;
import org.example.hotelmanagementsystem.mappers.OrderMapper;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.model.resDto.CustomerResDto;
import org.example.hotelmanagementsystem.model.resDto.OrderResDto;
import org.example.hotelmanagementsystem.repo.OrderRepository;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.example.hotelmanagementsystem.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @Override
    public HttpEntity<?> getMyOrders(Pageable pageable) {
        String email = currentUser.getMe();
        User customer = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Order> customerOrders = orderRepository.findAllByUser(customer);
        List<OrderResDto> orderResDtoList=new ArrayList<>();
        for (Order customerOrder : customerOrders) {
            orderResDtoList.add(orderMapper.toDto(customerOrder));
        }
        Page<OrderResDto> pagedOrders=new PageImpl<>(orderResDtoList,pageable,orderResDtoList.size());
        return ResponseEntity.ok(pagedOrders);
    }

    @Override
    public HttpEntity<?> getMe() {
        String email = currentUser.getMe();
        User customer = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerResDto dto = customerMapper.toDto(customer);
        return ResponseEntity.ok(dto);
    }

    @Override
    public HttpEntity<?> edit(RegisterDto customerDto, UUID id) {
        User entity = customerMapper.toEntity(customerDto);
        entity.setId(id);
        userRepository.save(entity);
        return ResponseEntity.ok("Customer edited");
    }
}
