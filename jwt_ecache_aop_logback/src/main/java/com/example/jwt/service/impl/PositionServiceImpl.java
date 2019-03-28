package com.example.jwt.service.impl;

import com.example.jwt.constants.PositionConstants;
import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.Position;
import com.example.jwt.service.PositionService;
import com.example.jwt.system.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position createPost(Position postition) {

        Position newPosition = new Position();

        newPosition.setPostKey(postition.getPostKey());
        newPosition.setPostName(postition.getPostName());
        newPosition.setPostSort(postition.getPostSort());
        newPosition.setEnabled(postition.getEnabled());
        newPosition.setIsDeleted(PositionConstants.Position_NOT_DELETED);

        return positionRepository.save(newPosition);
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Position> getPosition(Long id) {

        Optional<Position> position = positionRepository.findById(id);
        return position;
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);

    }
}
