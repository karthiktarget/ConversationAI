package com.example.ConversationAIBackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LLMService {

    private final WebClient webClient;

    public LLMService(@Value("${groq.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    public String getAnswerFromLLM(String prompt) {
        String requestBody = """
        {
            "model": "mixtral-8x7b-32768",
            "messages": [{"role": "user", "content": "%s"}],
            "temperature": 0.7
        }
        """.formatted(prompt);

        // Parse just the AI content from the JSON response
        String responseJson = webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // crude parse – ideally use Jackson to map JSON
        String marker = "\"content\":\"";
        int idx = responseJson.indexOf(marker);
        if (idx != -1) {
            int start = idx + marker.length();
            int end = responseJson.indexOf("\"", start);
            if (end > start) {
                return responseJson.substring(start, end);
            }
        }
        return "Sorry, I couldn’t generate a response.";
    }
}
