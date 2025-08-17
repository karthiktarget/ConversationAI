package com.example.ConversationAIBackend.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many messages belong to one session
    @ManyToOne
    @JoinColumn(name = "session_id")
    private ChatSession session;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Sender sender; // USER or AI

    @Column(columnDefinition = "text")
    private String message;

    // room for tool outputs, retrieval chunks, token counts, etc.
    @Column(columnDefinition = "text")
    private String metadataJson;

    private Instant createdAt;

    @PrePersist
    public void onCreate() { createdAt = Instant.from(LocalDateTime.now()); }

    public enum Sender { USER, AI }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ChatSession getSession() { return session; }
    public void setSession(ChatSession session) { this.session = session; }

    public Sender getSender() { return sender; }
    public void setSender(Sender sender) { this.sender = sender; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
