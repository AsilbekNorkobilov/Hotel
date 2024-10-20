package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "All order",description = "Endpoint returns all current customers orders")
    @GetMapping("orders")
    public HttpEntity<?> getMyOrders(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return customerService.getMyOrders(pageable);
    }

    @Operation(summary = "User", description = "Returns the id, email, first name and last name of the user")
    @GetMapping("me")
    public HttpEntity<?> getMe(){
        return customerService.getMe();
    }

    @Operation(summary = "Editing user", description = "You can change email, password, first name and last name")
    @PutMapping("{id}")
    public HttpEntity<?> editCustomer(@RequestBody RegisterDto customerDto, @PathVariable UUID id){
        return customerService.edit(customerDto,id);
    }

}
