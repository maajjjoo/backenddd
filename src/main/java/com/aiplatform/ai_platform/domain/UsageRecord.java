package com.aiplatform.ai_platform.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usage_records")
public class UsageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
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
