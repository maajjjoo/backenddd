package com.aiplatform.ai_platform.dto;

import java.time.LocalDate;

public class QuotaStatusResponse {

    private String plan;
    private long tokensUsed;
    private long tokensRemaining;
    private LocalDate resetDate;

    public QuotaStatusResponse() {}

    public QuotaStatusResponse(String plan, long tokensUsed, long tokensRemaining, LocalDate resetDate) {
        this.plan = plan;
        this.tokensUsed = tokensUsed;
        this.tokensRemaining = tokensRemaining;
        this.resetDate = resetDate;
    }

    public String getPlan() { return plan; }
    public long getTokensUsed() { return tokensUsed; }
    public long getTokensRemaining() { return tokensRemaining; }
    public LocalDate getResetDate() { return resetDate; }
}
