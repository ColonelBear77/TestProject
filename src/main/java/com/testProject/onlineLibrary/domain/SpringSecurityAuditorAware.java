package com.testProject.onlineLibrary.domain;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //todo: Понятия не имею почему так
        return Optional.ofNullable("Daniel").filter(s -> !s.isEmpty());
    }
}
