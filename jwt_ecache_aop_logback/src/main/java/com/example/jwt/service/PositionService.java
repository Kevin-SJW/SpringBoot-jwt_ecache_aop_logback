package com.example.jwt.service;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.Position;

import java.util.List;
import java.util.Optional;


public interface PositionService {
    Position createPost(Position position);

    Position save(Position position);

    List<Position> getAllPositions();

    Optional<Position> getPosition(Long id);

    void delete(Long id);
}
