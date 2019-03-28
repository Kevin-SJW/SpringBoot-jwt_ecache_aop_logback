package com.example.jwt.service.impl;

import com.example.jwt.domain.system.*;
import com.example.jwt.service.UserService;
import com.example.jwt.service.dto.UserDTO;
import com.example.jwt.system.AuthorityRepository;
import com.example.jwt.system.DepartmentRepository;
import com.example.jwt.system.PositionRepository;
import com.example.jwt.system.UserRepository;
import com.example.jwt.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;


@Service
@Slf4j

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public User createUser(UserDTO userDTO) {

        User user = new User();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setDisplayName(userDTO.getDisplayName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.getGender());
        user.setLastPasswordResetDate(new Date());
        user.setPhone(userDTO.getPhone());
        user.setMobile(userDTO.getMobile());
        user.setDeleted(false);
        user.setEnabled(true);
        if (userDTO.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        Department department = departmentRepository.findByDeptKey(userDTO.getDepartment());
        List<Position> positionList = new ArrayList<>();

        for (String postKey : userDTO.getPositions()) {
            positionList.add(positionRepository.findByPostKey(postKey));
        }

        List<Authority> authorityList = new ArrayList<>();

        for (String roleKey : userDTO.getAuthorities()) {
            authorityList.add(authorityRepository.findByRoleKey(roleKey));
        }

        user.setDepartment(department);
        user.setPositions(positionList);
        user.setAuthorities(authorityList);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }
    @Override
    public User save(UserDTO userDTO) {
//        delete(userDTO.getId());

        User user = new User();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setId(userDTO.getId());
        user.setDisplayName(userDTO.getDisplayName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.getGender());
        user.setLastPasswordResetDate(new Date());
        user.setPhone(userDTO.getPhone());
        user.setMobile(userDTO.getMobile());
        user.setDeleted(false);
        user.setEnabled(true);
        if (userDTO.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        Department department = departmentRepository.findByDeptKey(userDTO.getDepartment());
        List<Position> positionList = new ArrayList<>();

        for (String postKey : userDTO.getPositions()) {
            positionList.add(positionRepository.findByPostKey(postKey));
        }

        List<Authority> authorityList = new ArrayList<>();

        for (String roleKey : userDTO.getAuthorities()) {
            authorityList.add(authorityRepository.findByRoleKey(roleKey));
        }

        user.setDepartment(department);
        user.setPositions(positionList);
        user.setAuthorities(authorityList);
        userRepository.save(user);
        log.debug("update Information for User: {}", user);
        return user;
    }



    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllManagedUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}
