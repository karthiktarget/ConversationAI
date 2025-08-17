package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
