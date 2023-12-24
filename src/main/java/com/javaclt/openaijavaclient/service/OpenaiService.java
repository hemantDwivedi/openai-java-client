package com.javaclt.openaijavaclient.service;

import com.javaclt.openaijavaclient.model.OpenaiChatCompletion;
import com.javaclt.openaijavaclient.model.RoleContent;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenaiService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String CHAT_COMPLETIONS_URL = "https://api.openai.com/v1/chat/completions";
    private Environment env;

    public OpenaiService(Environment env) {
        this.env = env;
    }

    public String createChatCompletions(String content) {
        String openaiKey = env.getProperty("openai.key");
        logger.info("chat completions request payload: {} ", content);

        OpenaiChatCompletion openaiChatCompletion = chatCompletion(content);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openaiKey);
        HttpEntity<OpenaiChatCompletion> entity = new HttpEntity<>(openaiChatCompletion, headers);

        String response = restTemplate.postForObject(CHAT_COMPLETIONS_URL, entity, String.class);

        return response;
    }

    private OpenaiChatCompletion chatCompletion(String content){
        JSONObject jsonObject = new JSONObject(content);
        String userContent = jsonObject.get("prompt").toString();
        List<RoleContent> messages = new ArrayList<>();
        RoleContent systemRoleContent = new RoleContent("system", "You are a helpful assistant.");
        RoleContent userRoleContent = new RoleContent("user", userContent);

        messages.add(systemRoleContent);
        messages.add(userRoleContent);

        return new OpenaiChatCompletion(messages, "gpt-3.5-turbo");
    }
}
