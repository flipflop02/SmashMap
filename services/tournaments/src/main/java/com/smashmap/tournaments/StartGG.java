package com.smashmap.tournaments;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StartGG {
    private static HttpHeaders headers;

    private final StartGGConfig config;

    public StartGG(StartGGConfig config) {
        this.config = config;

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + config.getToken());
    }

    public String sendRequest(String query, HttpMethod method) {
        RestTemplate restTemplate = new RestTemplate();
        String bodyResponse;

        Map<String, Object> body = Map.of(
                "query", query
        );
        
        try {
            HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(config.getUrl(), method, entity, String.class);

            bodyResponse = response.getBody();
        }
        catch (Exception e) {
            bodyResponse = "err";
            System.out.println("** Exception: "+ e.getMessage());
        }

        return bodyResponse;
    }

}
