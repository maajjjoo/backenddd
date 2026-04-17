package com.aiplatform.ai_platform.exception;

public class QuotaExceededException extends RuntimeException {

    public QuotaExceededException() {
        super("Monthly quota exhausted. Upgrade your plan.");
    }
}
