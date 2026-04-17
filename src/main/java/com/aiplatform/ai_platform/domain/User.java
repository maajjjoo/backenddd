package com.aiplatform.ai_platform.domain;

import java.time.LocalDate;

public class User {

    private Long id;
    private String username;
    private Plan plan;
    private long tokensUsed = 0;
    private LocalDate quotaResetDate;

    public User() {}

    public User(Long id, String username, Plan plan, long tokensUsed, LocalDate quotaResetDate) {
        this.id = id;
        this.username = username;
        this.plan = plan;
        this.tokensUsed = tokensUsed;
        this.quotaResetDate = quotaResetDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public Plan getPlan() { return plan; }
    public long getTokensUsed() { return tokensUsed; }
    public LocalDate getQuotaResetDate() { return quotaResetDate; }

    public void setPlan(Plan plan) { this.plan = plan; }
    public void setTokensUsed(long tokensUsed) { this.tokensUsed = tokensUsed; }
    public void setQuotaResetDate(LocalDate quotaResetDate) { this.quotaResetDate = quotaResetDate; }
}
