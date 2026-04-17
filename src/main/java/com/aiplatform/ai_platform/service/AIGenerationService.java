package com.aiplatform.ai_platform.service;

import com.aiplatform.ai_platform.dto.GenerationRequest;
import com.aiplatform.ai_platform.dto.GenerationResponse;
import com.aiplatform.ai_platform.exception.QuotaExceededException;
import com.aiplatform.ai_platform.exception.RateLimitException;

public interface AIGenerationService {

    GenerationResponse generate(GenerationRequest request)
            throws RateLimitException, QuotaExceededException;
}
