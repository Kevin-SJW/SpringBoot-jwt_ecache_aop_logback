package com.example.jwt.service;

import com.example.jwt.domain.system.Authority;
import com.example.jwt.domain.system.Department;

import java.util.List;
import java.util.Optional;


public interface AuthorityService {
    Authority findById(Long id);

    Authority createAuthority(Authority authority);

    Authority save(Authority authority);

    List<Authority> getAllAuthorities();

    Optional<Authority> getAuthority(Long id);

    void delete(Long id);
}
