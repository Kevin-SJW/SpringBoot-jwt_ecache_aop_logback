package com.example.jwt.security.service;

import com.example.jwt.domain.system.User;


public interface AuthService {
    User register(User userToAdd);

    String login(String username, String password);

    String refresh(String oldToken);
}
