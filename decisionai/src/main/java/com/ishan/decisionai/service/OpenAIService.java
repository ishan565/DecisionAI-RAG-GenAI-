package com.ishan.decisionai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Service
public class OpenAIService {

    @Value("${azure.openai.api.key}")
    private String apiKey;

    @Value("${azure.openai.endpoint}")
    private String endpoint;

    @Value("${azure.openai.deployment}")
    private String deployment;

    public String generateExplanation(
            String decisionType,
            String recommendation,
            double scoreA,
            double scoreB,
            double confidence,
            String dominantFactor,
            String weakestFactor,
            String reasoning,
            List<String> facts
    ) {

        String knowledge = "- " + String.join("\n- ", facts);

        String prompt = """
You are an Explainable Decision AI.

Decision Type: %s
Winner: %s
Score A: %.2f
Score B: %.2f
Confidence: %.2f

Dominant factor: %s
Weakest factor: %s

Reasoning:
%s

Real world knowledge:
%s

Explain clearly to a normal human WHY this option wins.
Do not assume product specs.
Explain only based on decision factors.
""".formatted(decisionType,recommendation,scoreA,scoreB,confidence,
                dominantFactor,weakestFactor,reasoning,knowledge);


        String url = endpoint + "/openai/deployments/" + deployment +
                "/chat/completions?api-version=2024-02-15-preview";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("messages", List.of(Map.of("role","user","content",prompt)));
        body.put("temperature",0.2);
        body.put("max_tokens",400);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url,new HttpEntity<>(body,headers),Map.class);

        Map<String,Object> responseBody = response.getBody();

        if(responseBody == null || !responseBody.containsKey("choices"))
            return "Azure OpenAI Error: " + responseBody;

        List<Map<String,Object>> choices =
                (List<Map<String,Object>>) responseBody.get("choices");

        Map<String,Object> message =
                (Map<String,Object>) choices.get(0).get("message");

        return message.get("content").toString();
    }
}
