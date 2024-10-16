package org.example.hotelmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotelmanagementsystem.entity.enums.PayType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne
    private Order order;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PayType payType;
    @CreationTimestamp
    private LocalDateTime payedAt;


}