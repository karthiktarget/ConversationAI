package com.example.ConversationAIBackend.repository;


import com.example.ConversationAIBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
