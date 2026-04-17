package com.aiplatform.ai_platform.config;

import com.aiplatform.ai_platform.domain.Plan;
import com.aiplatform.ai_platform.domain.User;
import com.aiplatform.ai_platform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            LocalDate nextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1);
            userRepository.save(new User(null, "user_free",       Plan.FREE,       0, 0, nextMonth));
            userRepository.save(new User(null, "user_pro",        Plan.PRO,        0, 0, nextMonth));
            userRepository.save(new User(null, "user_enterprise", Plan.ENTERPRISE, 0, 0, null));
        }
    }
}
