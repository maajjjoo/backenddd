package com.aiplatform.ai_platform.repository;

import com.aiplatform.ai_platform.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idSeq.getAndIncrement());
        }
        store.put(user.getId(), user);
        return user;
    }

    public List<User> saveAll(List<User> users) {
        users.forEach(this::save);
        return users;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public long count() {
        return store.size();
    }
}
