package com.aiplatform.ai_platform.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Plan plan;

    @Column(nullable = false)
    private long tokensUsed = 0;

    @Column(nullable = false)
    private int requestsThisMinute = 0;

    private LocalDate quotaResetDate;

    public User() {}

    public User(Long id, String username, Plan plan, long tokensUsed, int requestsThisMinute, LocalDate quotaResetDate) {
        this.id = id;
        this.username = username;
        this.plan = plan;
        this.tokensUsed = tokensUsed;
        this.requestsThisMinute = requestsThisMinute;
        this.quotaResetDate = quotaResetDate;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public Plan getPlan() { return plan; }
    public long getTokensUsed() { return tokensUsed; }
    public int getRequestsThisMinute() { return requestsThisMinute; }
    public LocalDate getQuotaResetDate() { return quotaResetDate; }

    public void setPlan(Plan plan) { this.plan = plan; }
    public void setTokensUsed(long tokensUsed) { this.tokensUsed = tokensUsed; }
    public void setRequestsThisMinute(int requestsThisMinute) { this.requestsThisMinute = requestsThisMinute; }
    public void setQuotaResetDate(LocalDate quotaResetDate) { this.quotaResetDate = quotaResetDate; }
}
