package com.aiplatform.ai_platform.service;

import com.aiplatform.ai_platform.domain.User;
import com.aiplatform.ai_platform.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduledResetService {

    private final RateLimitProxyService rateLimitProxyService;
    private final UserRepository userRepository;

    public ScheduledResetService(RateLimitProxyService rateLimitProxyService, UserRepository userRepository) {
        this.rateLimitProxyService = rateLimitProxyService;
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = 60_000)
    public void resetRateLimitCounters() {
        rateLimitProxyService.resetCounters();
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void resetMonthlyQuotas() {
        List<User> users = userRepository.findAll();
        LocalDate nextReset = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        for (User user : users) {
            user.setTokensUsed(0);
            user.setQuotaResetDate(nextReset);
        }
        userRepository.saveAll(users);
    }
}
