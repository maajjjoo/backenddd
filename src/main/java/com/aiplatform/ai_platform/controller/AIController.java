package com.aiplatform.ai_platform.controller;

import com.aiplatform.ai_platform.dto.GenerationRequest;
import com.aiplatform.ai_platform.dto.GenerationResponse;
import com.aiplatform.ai_platform.service.AIGenerationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIGenerationService aiGenerationService;

    public AIController(@Qualifier("rateLimitProxy") AIGenerationService aiGenerationService) {
        this.aiGenerationService = aiGenerationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerationResponse> generate(@Valid @RequestBody GenerationRequest request) {
        GenerationResponse response = aiGenerationService.generate(request);
        return ResponseEntity.ok(response);
    }
}
