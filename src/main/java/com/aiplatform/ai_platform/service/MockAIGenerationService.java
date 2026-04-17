package com.aiplatform.ai_platform.service;

import com.aiplatform.ai_platform.dto.GenerationRequest;
import com.aiplatform.ai_platform.dto.GenerationResponse;
import com.aiplatform.ai_platform.util.TokenEstimator;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Random;

public class MockAIGenerationService implements AIGenerationService {

    private static final List<String> RESPONSES = List.of(
            "Quantum mechanics describes nature at the smallest scales of energy levels of atoms and subatomic particles.",
            "Artificial intelligence is the simulation of human intelligence processes by computer systems.",
            "The universe is approximately 13.8 billion years old, according to current cosmological models.",
            "Machine learning algorithms build a model based on sample data to make predictions or decisions.",
            "Neural networks are computing systems inspired by biological neural networks in animal brains.",
            "The theory of relativity transformed theoretical physics and astronomy during the 20th century.",
            "DNA carries the genetic instructions used in the growth, development, and functioning of all living organisms.",
            "Climate change refers to long-term shifts in global temperatures and weather patterns.",
            "Blockchain is a distributed ledger technology that records transactions across many computers.",
            "The human brain contains approximately 86 billion neurons connected by trillions of synapses."
    );

    private final int delayMs;
    private final Random random = new Random();

    public MockAIGenerationService(@Value("${app.mock.response.delay-ms:1200}") int delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public GenerationResponse generate(GenerationRequest request) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String text = RESPONSES.get(random.nextInt(RESPONSES.size()));
        int tokens = TokenEstimator.estimate(request.getPrompt());
        return new GenerationResponse(text, tokens, 0);
    }
}
