package com.example.jwt.security;


import com.example.jwt.constants.Constants;
import com.example.jwt.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> userName = SecurityUtils.getCurrentUserLogin();
        if (userName.isPresent()) {
            Optional<String> ofOptional = Optional.of(Constants.SYSTEM_ACCOUNT);
            return ofOptional;
        }
        return userName;
    }
}
