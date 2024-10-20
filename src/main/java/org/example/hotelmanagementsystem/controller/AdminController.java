package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.service.CustomerService;
import org.example.hotelmanagementsystem.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @Operation(summary = "Get all tasks",description = "Endpoint to get all orders, Accessible only to users with the 'ROLE_ADMIN' role")
    @GetMapping("orders")
    public HttpEntity<?> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return orderService.getAllOrders(pageable);
    }

    @Operation(summary = "Get all customers",description = "Endpoint to get all customers, Accessible only to users with the 'ROLE_ADMIN' role")
    @GetMapping("customers")
    public HttpEntity<?> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return customerService.getAllCustomers(pageable);
    }

    @Operation(summary = "Get all admins",description = "Endpoint to get all admins, Accessible only to users with the 'ROLE_ADMIN' role")
    @GetMapping("admins")
    public HttpEntity<?> getAllAdmins(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return customerService.getAllAdmins(pageable);
    }

    @Operation(summary = "Creating new Admin",description = "Endpoint to creating a new admin from existing Users, Accessible only to users with the 'ROLE_ADMIN' role")
    @PatchMapping("make-admin/{id}")
    public HttpEntity<?> makeAdmin(@PathVariable UUID id){
        return customerService.makeAdmin(id);
    }
}
