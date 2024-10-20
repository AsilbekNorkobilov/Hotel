package org.example.hotelmanagementsystem.component;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.*;
import org.example.hotelmanagementsystem.entity.enums.RoleName;
import org.example.hotelmanagementsystem.entity.enums.RoomType;
import org.example.hotelmanagementsystem.repo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final OrderRepository orderRepository;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create")){
            Role roleAdmin=new Role(1, RoleName.ROLE_ADMIN);
            Role roleUser=new Role(2,RoleName.ROLE_CUSTOMER);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            User admin=User.builder()
                    .email("a@gmail.com")
                    .firstName("A")
                    .lastName("AA")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleAdmin))
                    .build();
            User user=User.builder()
                    .email("b@gmail.com")
                    .firstName("B")
                    .lastName("BB")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleUser))
                    .build();
            userRepository.save(admin);
            userRepository.save(user);

            Hotel hotel=Hotel.builder()
                    .email("Hotel@gmail.com")
                    .address("Brbalo brbalo")
                    .phone("+998977777777")
                    .name("Yulduz")
                    .build();
            hotelRepository.save(hotel);

            Room room1= Room.builder()
                    .bedsCount(2)
                    .floor(1)
                    .hotel(hotel)
                    .price(2000d)
                    .roomNumber(1)
                    .roomType(RoomType.STANDARD)
                    .build();

            Room room2= Room.builder()
                    .bedsCount(2)
                    .floor(1)
                    .hotel(hotel)
                    .price(2000d)
                    .roomNumber(2)
                    .roomType(RoomType.STANDARD)
                    .build();

            roomRepository.save(room1);
            roomRepository.save(room2);

            Order order1= Order.builder()
                    .room(room1)
                    .user(user)
                    .checkIn(LocalDate.of(2024,10,20))
                    .checkOut(LocalDate.of(2024,10,23))
                    .build();

            Order order2= Order.builder()
                    .room(room1)
                    .user(user)
                    .checkIn(LocalDate.of(2024,10,26))
                    .checkOut(LocalDate.of(2024,10,28))
                    .build();

            orderRepository.save(order1);
            orderRepository.save(order2);
        }
    }
}
