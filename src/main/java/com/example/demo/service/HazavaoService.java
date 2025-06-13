package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HazavaoService {
    private final OpenAiClient openAiClient;

    public String getDefinition(String teny) {
        String prompt = "Hazavao amin'ny teny malagasy ny dikan'ny teny: " + teny;
        return openAiClient.getChatCompletion(prompt);
    }
}
