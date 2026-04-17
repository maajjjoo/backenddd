package com.aiplatform.ai_platform.repository;

import com.aiplatform.ai_platform.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
