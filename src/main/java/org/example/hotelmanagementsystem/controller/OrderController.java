package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.DownloadOrderDto;
import org.example.hotelmanagementsystem.model.reqDto.OrderReqDto;
import org.example.hotelmanagementsystem.model.reqDto.RateDto;
import org.example.hotelmanagementsystem.service.OrderService;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;


    @Operation(summary = "Creating new order",description = "Save a new order with the provided details if it possible")
    @PostMapping()
    public HttpEntity<?> makeOrder(@RequestBody OrderReqDto orderReqDto){
        return orderService.makeOrder(orderReqDto);
    }

    @Operation(summary = "Rate order",description = "Endpoint to write a comment and rate the room")
    @PostMapping("rate/{id}")
    public HttpEntity<?> rateOrder(@PathVariable UUID id, @RequestBody RateDto rateDto){
        return orderService.rateOrder(id,rateDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "download orders",description = "to download an excel file with orders during the specified period. Accessible only to users with the 'ROLE_ADMIN' role")
    @GetMapping("download")
    public HttpEntity<?> downloadOrders(@RequestBody DownloadOrderDto downloadOrderDto){
        return orderService.downloadOrderFile(downloadOrderDto);
    }


}
