package com.aiplatform.ai_platform.service;

import com.aiplatform.ai_platform.domain.Plan;
import com.aiplatform.ai_platform.domain.User;
import com.aiplatform.ai_platform.dto.DailyUsage;
import com.aiplatform.ai_platform.dto.QuotaStatusResponse;
import com.aiplatform.ai_platform.repository.UsageRecordRepository;
import com.aiplatform.ai_platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotaService {

    private final UserRepository userRepository;
    private final UsageRecordRepository usageRecordRepository;

    public QuotaService(UserRepository userRepository, UsageRecordRepository usageRecordRepository) {
        this.userRepository = userRepository;
        this.usageRecordRepository = usageRecordRepository;
    }

    public QuotaStatusResponse getStatus(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        long maxTokens = user.getPlan().maxTokensPerMonth();
        long remaining = maxTokens == Long.MAX_VALUE ? Long.MAX_VALUE : maxTokens - user.getTokensUsed();

        return new QuotaStatusResponse(
                user.getPlan().name(),
                user.getTokensUsed(),
                remaining,
                user.getQuotaResetDate()
        );
    }

    public List<DailyUsage> getHistory(Long userId) {
        LocalDate to = LocalDate.now();
        LocalDate from = to.minusDays(6);
        return usageRecordRepository.findByUserIdAndDateBetween(userId, from, to)
                .stream()
                .map(r -> new DailyUsage(r.getDate(), r.getTokensUsed()))
                .collect(Collectors.toList());
    }

    public QuotaStatusResponse upgradePlan(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        if (user.getPlan() == Plan.FREE) {
            user.setPlan(Plan.PRO);
        } else if (user.getPlan() == Plan.PRO) {
            user.setPlan(Plan.ENTERPRISE);
        }

        userRepository.save(user);

        long maxTokens = user.getPlan().maxTokensPerMonth();
        long remaining = maxTokens == Long.MAX_VALUE ? Long.MAX_VALUE : maxTokens - user.getTokensUsed();

        return new QuotaStatusResponse(user.getPlan().name(), user.getTokensUsed(), remaining, user.getQuotaResetDate());
    }
}
