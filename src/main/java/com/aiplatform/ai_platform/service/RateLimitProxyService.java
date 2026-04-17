package com.aiplatform.ai_platform.service;

import com.aiplatform.ai_platform.domain.User;
import com.aiplatform.ai_platform.dto.GenerationRequest;
import com.aiplatform.ai_platform.dto.GenerationResponse;
import com.aiplatform.ai_platform.exception.RateLimitException;
import com.aiplatform.ai_platform.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitProxyService implements AIGenerationService {

    private final AIGenerationService next;
    private final UserRepository userRepository;
    private final ConcurrentHashMap<Long, AtomicInteger> requestCounters = new ConcurrentHashMap<>();
    private volatile LocalDateTime windowStart = LocalDateTime.now();

    public RateLimitProxyService(AIGenerationService next, UserRepository userRepository) {
        this.next = next;
        this.userRepository = userRepository;
    }

    @Override
    public GenerationResponse generate(GenerationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));

        int maxRequests = user.getPlan().maxRequestsPerMinute();

        AtomicInteger counter = requestCounters.computeIfAbsent(request.getUserId(), id -> new AtomicInteger(0));
        int current = counter.incrementAndGet();

        if (maxRequests != Integer.MAX_VALUE && current > maxRequests) {
            counter.decrementAndGet();
            long secondsUntilReset = 60 - ChronoUnit.SECONDS.between(windowStart, LocalDateTime.now());
            throw new RateLimitException(Math.max(1, secondsUntilReset));
        }

        return next.generate(request);
    }

    public void resetCounters() {
        requestCounters.clear();
        windowStart = LocalDateTime.now();
    }
}
