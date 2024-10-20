package org.example.hotelmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    private Room room;
    @ManyToOne
    private User user;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer rate;
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;
}