package com.aiplatform.ai_platform.controller;

import com.aiplatform.ai_platform.dto.DailyUsage;
import com.aiplatform.ai_platform.dto.QuotaStatusResponse;
import com.aiplatform.ai_platform.dto.UpgradeRequest;
import com.aiplatform.ai_platform.service.QuotaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quota")
public class QuotaController {

    private final QuotaService quotaService;

    public QuotaController(QuotaService quotaService) {
        this.quotaService = quotaService;
    }

    @GetMapping("/status")
    public ResponseEntity<QuotaStatusResponse> getStatus(@RequestParam Long userId) {
        return ResponseEntity.ok(quotaService.getStatus(userId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<DailyUsage>> getHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(quotaService.getHistory(userId));
    }

    @PostMapping("/upgrade")
    public ResponseEntity<QuotaStatusResponse> upgrade(@Valid @RequestBody UpgradeRequest request) {
        return ResponseEntity.ok(quotaService.upgradePlan(request.getUserId()));
    }
}
