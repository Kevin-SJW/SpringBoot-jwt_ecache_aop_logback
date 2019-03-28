package com.example.jwt.service.impl;

import com.example.jwt.constants.MenuConstants;
import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.Menu;
import com.example.jwt.service.MenuService;
import com.example.jwt.system.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.awt.SystemColor.menu;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu createMenu(Menu menu) {
        Menu newMenu = new Menu();

        newMenu.setMenuName(menu.getMenuName());
        newMenu.setParentId(menu.getParentId());
        newMenu.setMenuKey(menu.getMenuKey());
        newMenu.setMenuType(menu.getMenuType());
        newMenu.setEnabled(menu.getEnabled());
        newMenu.setIsDeleted(MenuConstants.MENU_NOT_DELETED);
        newMenu.setRemarks(menu.getRemarks());

        return menuRepository.save(newMenu);
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Menu> getMenu(Long id) {

        Optional<Menu> menu = menuRepository.findById(id);
        return menu;

    }

    @Override
    public void delete(Long id) {
        menuRepository.deleteById(id);

    }
}
