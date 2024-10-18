package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("orders")
    public HttpEntity<?> getMyOrders(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return customerService.getMyOrders(pageable);
    }

    @GetMapping("me")
    public HttpEntity<?> getMe(){
        return customerService.getMe();
    }

    @PutMapping("{id}")
    public HttpEntity<?> editCustomer(@RequestBody RegisterDto customerDto, @PathVariable UUID id){
        return customerService.edit(customerDto,id);
    }
}
