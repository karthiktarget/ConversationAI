package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
