package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
