package com.aiplatform.ai_platform.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.aiplatform.ai_platform.repository.UsageRecordRepository;
import com.aiplatform.ai_platform.repository.UserRepository;
import com.aiplatform.ai_platform.service.AIGenerationService;
import com.aiplatform.ai_platform.service.MockAIGenerationService;
import com.aiplatform.ai_platform.service.QuotaProxyService;
import com.aiplatform.ai_platform.service.RateLimitProxyService;

@Configuration
public class AppConfig {

    @Bean("mockAIService")
    public AIGenerationService mockAIService(@Value("${app.mock.response.delay-ms:1200}") int delayMs) {
        return new MockAIGenerationService(delayMs);
    }

    @Bean("quotaProxy")
    public AIGenerationService quotaProxy(
            @Qualifier("mockAIService") AIGenerationService mockAIService,
            UserRepository userRepository,
            UsageRecordRepository usageRecordRepository) {
        return new QuotaProxyService(mockAIService, userRepository, usageRecordRepository);
    }

    @Bean
    @Primary
    public RateLimitProxyService rateLimitProxy(
            @Qualifier("quotaProxy") AIGenerationService quotaProxy,
            UserRepository userRepository) {
        return new RateLimitProxyService(quotaProxy, userRepository);
    }
}
