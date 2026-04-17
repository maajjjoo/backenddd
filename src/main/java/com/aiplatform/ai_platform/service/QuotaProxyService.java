package com.aiplatform.ai_platform.service;

import com.aiplatform.ai_platform.domain.User;
import com.aiplatform.ai_platform.domain.UsageRecord;
import com.aiplatform.ai_platform.dto.GenerationRequest;
import com.aiplatform.ai_platform.dto.GenerationResponse;
import com.aiplatform.ai_platform.exception.QuotaExceededException;
import com.aiplatform.ai_platform.repository.UsageRecordRepository;
import com.aiplatform.ai_platform.repository.UserRepository;
import com.aiplatform.ai_platform.util.TokenEstimator;

import java.time.LocalDate;
import java.util.Optional;

public class QuotaProxyService implements AIGenerationService {

    private final AIGenerationService next;
    private final UserRepository userRepository;
    private final UsageRecordRepository usageRecordRepository;

    public QuotaProxyService(AIGenerationService next,
                              UserRepository userRepository,
                              UsageRecordRepository usageRecordRepository) {
        this.next = next;
        this.userRepository = userRepository;
        this.usageRecordRepository = usageRecordRepository;
    }

    @Override
    public GenerationResponse generate(GenerationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

        int estimated = TokenEstimator.estimate(request.getPrompt());
        long maxTokens = user.getPlan().maxTokensPerMonth();

        if (maxTokens != Long.MAX_VALUE && user.getTokensUsed() + estimated > maxTokens) {
            throw new QuotaExceededException();
        }

        GenerationResponse response = next.generate(request);

        int consumed = response.getTokensConsumed();
        user.setTokensUsed(user.getTokensUsed() + consumed);
        long remaining = maxTokens == Long.MAX_VALUE ? Long.MAX_VALUE : maxTokens - user.getTokensUsed();
        userRepository.save(user);

        saveUsageRecord(user.getId(), consumed);

        return new GenerationResponse(response.getText(), consumed, remaining);
    }

    private void saveUsageRecord(Long userId, long tokens) {
        LocalDate today = LocalDate.now();
        Optional<UsageRecord> existing = usageRecordRepository.findByUserIdAndDate(userId, today);
        if (existing.isPresent()) {
            UsageRecord record = existing.get();
            record.setTokensUsed(record.getTokensUsed() + tokens);
            usageRecordRepository.save(record);
        } else {
            usageRecordRepository.save(new UsageRecord(userId, today, tokens));
        }
    }
}
