package org.example.hotelmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.OrderReqDto;
import org.example.hotelmanagementsystem.model.reqDto.RateDto;
import org.example.hotelmanagementsystem.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("all")
    public HttpEntity<?> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return orderService.getAllOrders(pageable);
    }

    @PostMapping()
    public HttpEntity<?> makeOrder(@RequestBody OrderReqDto orderReqDto){
        return orderService.makeOrder(orderReqDto);
    }

    @PostMapping("rate/{id}")
    public HttpEntity<?> rateOrder(@PathVariable UUID id, @RequestBody RateDto rateDto){
        return orderService.rateOrder(id,rateDto);
    }


}
