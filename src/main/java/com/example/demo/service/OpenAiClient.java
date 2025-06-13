package com.example.demo.service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiClient {
  @Value("${openai.api.key}")
  private String apiKey;

  private final WebClient webClient =
      WebClient.builder()
          .baseUrl("https://api.openai.com/v1/chat/completions")
          .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .build();

  public String getChatCompletion(String prompt) {
    Map<String, Object> message = Map.of("role", "user", "content", prompt);

    Map<String, Object> body = Map.of("model", "gpt-3.5-turbo", "messages", List.of(message));

    return webClient
        .post()
        .header("Authorization", "Bearer " + apiKey)
        .bodyValue(body)
        .retrieve()
        .bodyToMono(Map.class)
        .map(
            response -> {
              List<Map<String, Object>> choices =
                  (List<Map<String, Object>>) response.get("choices");
              Map<String, Object> messageObj = (Map<String, Object>) choices.get(0).get("message");
              return (String) messageObj.get("content");
            })
        .block();
  }
}
