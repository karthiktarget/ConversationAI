package com.example.ConversationAIBackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;
    private String gender;
    private Instant createdAt;;
    private Instant returnedAt;
    private Instant shippedAt;
    private Instant deliveredAt;
    private Integer numOfItem;
}
