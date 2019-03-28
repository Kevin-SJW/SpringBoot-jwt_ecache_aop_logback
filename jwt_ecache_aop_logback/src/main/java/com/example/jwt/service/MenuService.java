package com.example.jwt.service;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.Menu;

import java.util.List;
import java.util.Optional;


public interface MenuService {
    Menu createMenu(Menu menu);

    Menu save(Menu menu);

    List<Menu> getAllMenus();

    Optional<Menu> getMenu(Long id);

    void delete(Long id);
}
