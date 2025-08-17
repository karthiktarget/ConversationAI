package com.example.ConversationAIBackend.dto;

public class ChatRequest {
    private Long userId;          // required
    private Long conversationId;  // optional
    private String message;       // required

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getConversationId() { return conversationId; }
    public void setConversationId(Long conversationId) { this.conversationId = conversationId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
