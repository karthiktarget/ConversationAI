package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
