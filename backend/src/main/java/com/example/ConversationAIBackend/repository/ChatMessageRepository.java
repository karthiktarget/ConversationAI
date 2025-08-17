package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.entity.ChatMessage;
import com.example.ConversationAIBackend.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySessionOrderByCreatedAtAscIdAsc(ChatSession session);
}
