package com.aiplatform.ai_platform.dto;

import java.time.LocalDate;

public class DailyUsage {

    private LocalDate date;
    private long tokensUsed;

    public DailyUsage() {}

    public DailyUsage(LocalDate date, long tokensUsed) {
        this.date = date;
        this.tokensUsed = tokensUsed;
    }

    public LocalDate getDate() { return date; }
    public long getTokensUsed() { return tokensUsed; }
}
