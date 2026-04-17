package com.aiplatform.ai_platform.config;

import com.aiplatform.ai_platform.repository.UsageRecordRepository;
import com.aiplatform.ai_platform.repository.UserRepository;
import com.aiplatform.ai_platform.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean("mockAIService")
    public AIGenerationService mockAIService(@Value("${app.mock.response.delay-ms:1200}") int delayMs) {
        return new MockAIGenerationService(delayMs);
    }

    @Bean("quotaProxy")
    public AIGenerationService quotaProxy(
            AIGenerationService mockAIService,
            UserRepository userRepository,
            UsageRecordRepository usageRecordRepository) {
        return new QuotaProxyService(mockAIService, userRepository, usageRecordRepository);
    }

    @Bean
    @Primary
    public RateLimitProxyService rateLimitProxy(
            AIGenerationService quotaProxy,
            UserRepository userRepository) {
        return new RateLimitProxyService(quotaProxy, userRepository);
    }
}
