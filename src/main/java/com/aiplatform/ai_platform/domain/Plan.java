package com.aiplatform.ai_platform.domain;

public enum Plan {
    FREE, PRO, ENTERPRISE;

    public int maxRequestsPerMinute() {
        return switch (this) {
            case FREE -> 10;
            case PRO -> 60;
            case ENTERPRISE -> Integer.MAX_VALUE;
        };
    }

    public long maxTokensPerMonth() {
        return switch (this) {
            case FREE -> 50_000L;
            case PRO -> 500_000L;
            case ENTERPRISE -> Long.MAX_VALUE;
        };
    }
}
