package com.example.jwt.rest.system;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.Menu;
import com.example.jwt.domain.system.Position;
import com.example.jwt.service.MenuService;
import com.example.jwt.utils.HeaderUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;


@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "MenuRestController", description = "menu API")
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menus")
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody Menu menu) throws URISyntaxException {
        log.debug("REST request to save Menu : {}", menu);

        Menu newMenu = menuService.createMenu(menu);
        return ResponseEntity.created(new URI("/api/menus/" + menu.getMenuName()))
                .headers(HeaderUtil.createAlert("menuManagement.created", menu.getMenuName()))
                .body(newMenu);
    }

    @PutMapping("/menus")
    public ResponseEntity<Menu> updateMenu(@Valid @RequestBody Menu menu) throws URISyntaxException {
        log.debug("REST request to update Menu : {}", menu);

        Menu newMenu = menuService.save(menu);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menu.getId().toString()))
                .body(newMenu);
    }

    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> getAllMenus() throws URISyntaxException {
        log.debug("REST request to get Menu : {}");
        List<Menu> newMenu = menuService.getAllMenus();
        return new ResponseEntity<>(newMenu, HttpStatus.OK);
    }

    @GetMapping( "/menus/{id}")
    public ResponseEntity getMenu(@PathVariable Long id) throws URISyntaxException {
        log.info("REST request to get single Menu : {}",id);
        Optional<Menu> menu = menuService.getMenu(id);

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        log.debug("REST request to delete Menu : {}", id);
        menuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
