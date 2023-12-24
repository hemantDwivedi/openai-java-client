package com.javaclt.openaijavaclient.controller;

import com.javaclt.openaijavaclient.service.OpenaiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/openai")
public class OpenaiController {
    private OpenaiService openaiService;

    public OpenaiController(OpenaiService openaiService) {
        this.openaiService = openaiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chatCompletions(@RequestBody String content){
        return ResponseEntity.ok(openaiService.createChatCompletions(content));
    }
}
