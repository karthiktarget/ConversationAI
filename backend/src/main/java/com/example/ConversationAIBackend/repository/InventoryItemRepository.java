package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
