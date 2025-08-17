package com.example.ConversationAIBackend.controller;

import com.example.ConversationAIBackend.dto.ChatRequest;
import com.example.ConversationAIBackend.dto.ChatResponse;
import com.example.ConversationAIBackend.entity.ChatMessage;
import com.example.ConversationAIBackend.entity.ChatSession;
import com.example.ConversationAIBackend.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        Long conversationId = request.getConversationId();

        // If no conversationId, start a new session
        if (conversationId == null) {
            ChatSession session = chatService.startSession(request.getUserId(), "New Conversation");
            conversationId = session.getId();
        }

        // Save user message
        ChatMessage userMsg = chatService.addUserMessage(conversationId, request.getMessage());

        // TODO: Replace this with AI/NLP integration
        String aiReply = "You said: " + request.getMessage();

        // Save AI reply
        chatService.addAiMessage(conversationId, aiReply, null);

        // Return response
        return new ChatResponse(conversationId, aiReply);
    }
}
