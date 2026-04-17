package com.aiplatform.ai_platform.dto;

import jakarta.validation.constraints.NotNull;

public class UpgradeRequest {

    @NotNull
    private Long userId;

    public UpgradeRequest() {}

    public UpgradeRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() { return userId; }
}
