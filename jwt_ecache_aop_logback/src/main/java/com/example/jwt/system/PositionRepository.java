package com.example.jwt.system;

import com.example.jwt.domain.system.Position;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPostKey(String postKey);
}
