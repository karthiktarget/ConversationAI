package com.example.ConversationAIBackend.service;

import com.example.ConversationAIBackend.entity.*;
import com.example.ConversationAIBackend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChatService {

    private final ChatSessionRepository sessionRepo;
    private final ChatMessageRepository messageRepo;
    private final UserRepository userRepo;

    public ChatService(ChatSessionRepository sessionRepo,
                       ChatMessageRepository messageRepo,
                       UserRepository userRepo) {
        this.sessionRepo = sessionRepo;
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public ChatSession startSession(Long userId, String title) {
        User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ChatSession s = new ChatSession();
        s.setUser(user);
        s.setTitle(title);
        return sessionRepo.save(s);
    }

    @Transactional
    public ChatMessage addUserMessage(Long sessionId, String text) {
        ChatSession s = sessionRepo.findById(sessionId).orElseThrow();
        ChatMessage m = new ChatMessage();
        m.setSession(s);
        m.setSender(ChatMessage.Sender.USER);
        m.setMessage(text);
        ChatMessage saved = messageRepo.save(m);
        sessionRepo.save(s);
        return saved;
    }

    @Transactional
    public ChatMessage addAiMessage(Long sessionId, String text, String metadataJson) {
        ChatSession s = sessionRepo.findById(sessionId).orElseThrow();
        ChatMessage m = new ChatMessage();
        m.setSession(s);
        m.setSender(ChatMessage.Sender.AI);
        m.setMessage(text);
        m.setMetadataJson(metadataJson);
        ChatMessage saved = messageRepo.save(m);
        sessionRepo.save(s);
        return saved;
    }

    public Optional<ChatSession> getSession(Long id) {
        return sessionRepo.findById(id);
    }
}
