package com.aiplatform.ai_platform.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.aiplatform.ai_platform.domain.UsageRecord;

@Repository
public class UsageRecordRepository {

    // key: "userId:date"
    private final Map<String, UsageRecord> store = new ConcurrentHashMap<>();

    private String key(Long userId, LocalDate date) {
        return userId + ":" + date;
    }

    public UsageRecord save(UsageRecord record) {
        store.put(key(record.getUserId(), record.getDate()), record);
        return record;
    }

    public Optional<UsageRecord> findByUserIdAndDate(Long userId, LocalDate date) {
        return Optional.ofNullable(store.get(key(userId, date)));
    }

    public List<UsageRecord> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to) {
        return store.values().stream()
                .filter(r -> r.getUserId().equals(userId)
                        && !r.getDate().isBefore(from)
                        && !r.getDate().isAfter(to))
                .collect(Collectors.toList());
    }
}
