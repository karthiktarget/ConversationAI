package com.example.ConversationAIBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long orderId;

    private Long userId;
    private String status;
    private String gender;

    private LocalDateTime createdAt;
    private LocalDateTime returnedAt;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;

    private Integer numOfItem;

}

