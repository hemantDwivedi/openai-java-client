package com.javaclt.openaijavaclient.model;

import java.util.List;

public class OpenaiChatCompletion {
    private List<RoleContent> messages;
    private String model;

    public OpenaiChatCompletion() {
    }

    public OpenaiChatCompletion(List<RoleContent> messages, String model) {
        this.messages = messages;
        this.model = model;
    }

    public List<RoleContent> getMessages() {
        return messages;
    }

    public void setMessages(List<RoleContent> messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "{" +
                "\"model\":" +
                model +
                "\"messages\":" +
                messages +
                "}";
    }
}
