package com.aiplatform.ai_platform.repository;

import com.aiplatform.ai_platform.domain.UsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsageRecordRepository extends JpaRepository<UsageRecord, Long> {

    List<UsageRecord> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);

    Optional<UsageRecord> findByUserIdAndDate(Long userId, LocalDate date);
}
