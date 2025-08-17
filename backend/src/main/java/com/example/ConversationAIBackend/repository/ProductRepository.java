package com.example.ConversationAIBackend.repository;


import com.example.ConversationAIBackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
