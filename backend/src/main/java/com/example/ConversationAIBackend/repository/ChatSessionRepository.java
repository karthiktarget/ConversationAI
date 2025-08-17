package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.entity.ChatSession;
import com.example.ConversationAIBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
    List<ChatSession> findByUserOrderByUpdatedAtDesc(User user);
}

