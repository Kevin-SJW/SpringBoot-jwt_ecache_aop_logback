package com.example.jwt.security;

import com.example.jwt.domain.system.User;


public class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities(),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }
}
