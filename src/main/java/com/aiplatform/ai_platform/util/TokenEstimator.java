package com.aiplatform.ai_platform.util;

public class TokenEstimator {

    private TokenEstimator() {}

    public static int estimate(String prompt) {
        if (prompt == null || prompt.isBlank()) return 1;
        String[] words = prompt.trim().split("\\s+");
        int estimated = (int) Math.ceil(words.length * 1.3);
        return Math.max(1, estimated);
    }
}
