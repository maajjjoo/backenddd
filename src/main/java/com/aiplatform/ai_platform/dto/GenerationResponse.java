package com.aiplatform.ai_platform.dto;

public class GenerationResponse {

    private String text;
    private int tokensConsumed;
    private long remainingTokens;

    public GenerationResponse() {}

    public GenerationResponse(String text, int tokensConsumed, long remainingTokens) {
        this.text = text;
        this.tokensConsumed = tokensConsumed;
        this.remainingTokens = remainingTokens;
    }

    public String getText() { return text; }
    public int getTokensConsumed() { return tokensConsumed; }
    public long getRemainingTokens() { return remainingTokens; }
}
