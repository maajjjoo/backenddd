package com.aiplatform.ai_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GenerationRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String prompt;

    public GenerationRequest() {}

    public GenerationRequest(Long userId, String prompt) {
        this.userId = userId;
        this.prompt = prompt;
    }

    public Long getUserId() { return userId; }
    public String getPrompt() { return prompt; }
}
