package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.component.CurrentUser;
import org.example.hotelmanagementsystem.entity.Order;
import org.example.hotelmanagementsystem.entity.Room;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.mappers.OrderMapper;
import org.example.hotelmanagementsystem.model.reqDto.OrderReqDto;
import org.example.hotelmanagementsystem.model.reqDto.RateDto;
import org.example.hotelmanagementsystem.model.resDto.OrderResDto;
import org.example.hotelmanagementsystem.repo.OrderRepository;
import org.example.hotelmanagementsystem.repo.RoomRepository;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.example.hotelmanagementsystem.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    @Override
    public HttpEntity<?> getAllOrders(Pageable pageable) {
        List<Order> all = orderRepository.findAll();
        List<OrderResDto> collect = all.stream().map(orderMapper::toDto).collect(Collectors.toList());
        Page<OrderResDto> pagedOrder=new PageImpl<>(collect,pageable,collect.size());
        return ResponseEntity.ok(pagedOrder);
    }

    @Override
    public HttpEntity<?> makeOrder(OrderReqDto orderReqDto) {
        List<Room> rooms = roomRepository.allEmptyRooms(orderReqDto.getRoomRoomType(), orderReqDto.getRoomBedsCount(), orderReqDto.getCheckIn(), orderReqDto.getCheckOut());
        if (!rooms.isEmpty()){
            String email = currentUser.getMe();
            User user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            Room selectedRoom = rooms.get(0);
            Order order=Order.builder()
                    .user(user)
                    .room(selectedRoom)
                    .checkIn(orderReqDto.getCheckIn())
                    .checkOut(orderReqDto.getCheckOut())
                    .build();
            orderRepository.save(order);
            return ResponseEntity.ok("Room is booked. Your room is "+selectedRoom.getRoomNumber()+" on "+ selectedRoom.getFloor()+" floor");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("Sorry, On this date we have no free rooms with this credentials");
        }
    }

    @Override
    public HttpEntity<?> rateOrder(UUID id, RateDto rateDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setRate(rateDto.getRate());
        order.setComment(rateDto.getComment());
        orderRepository.save(order);
        return ResponseEntity.ok("Thank you for your feedback");
    }
}
