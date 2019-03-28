package com.example.jwt.system;

import com.example.jwt.domain.system.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByRoleKey(String roleKey);
}
