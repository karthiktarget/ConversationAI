package com.example.ConversationAIBackend.controller;

import com.example.ConversationAIBackend.dto.ChatRequest;
import com.example.ConversationAIBackend.dto.ChatResponse;
import com.example.ConversationAIBackend.entity.ChatMessage;
import com.example.ConversationAIBackend.entity.ChatSession;
import com.example.ConversationAIBackend.service.ChatService;
import com.example.ConversationAIBackend.service.LLMService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final LLMService llmService;

    public ChatController(ChatService chatService, LLMService llmService) {
        this.chatService = chatService;
        this.llmService = llmService;
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

        // Call LLM for AI response
        String aiReply = llmService.getAnswerFromLLM(request.getMessage());

        // Save AI reply
        chatService.addAiMessage(conversationId, aiReply, null);

        // Return response
        return new ChatResponse(conversationId, aiReply);
    }
}
