package com.example.jwt.service;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.User;
import com.example.jwt.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User createUser(UserDTO userDTO);

    List<UserDTO> getAllManagedUsers();

    Optional<User> getUserWithAuthorities();

    User save(UserDTO userDTO);

    void delete(Long id);
}
