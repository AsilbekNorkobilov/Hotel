package org.example.hotelmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hotelmanagementsystem.entity.enums.RoomType;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    private Hotel hotel;

    private Integer floor;

    private Integer bedsCount;

    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Double price;

    @Column(columnDefinition = "boolean default false")
    private boolean isArchived;


}