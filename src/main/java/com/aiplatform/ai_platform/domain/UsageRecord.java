package com.aiplatform.ai_platform.domain;

import java.time.LocalDate;

public class UsageRecord {

    private Long id;
    private Long userId;
    private LocalDate date;
    private long tokensUsed = 0;

    public UsageRecord() {}

    public UsageRecord(Long userId, LocalDate date, long tokensUsed) {
        this.userId = userId;
        this.date = date;
        this.tokensUsed = tokensUsed;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public LocalDate getDate() { return date; }
    public long getTokensUsed() { return tokensUsed; }
    public void setTokensUsed(long tokensUsed) { this.tokensUsed = tokensUsed; }
}
