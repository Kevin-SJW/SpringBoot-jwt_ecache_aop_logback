package com.example.jwt.rest.system;

import com.example.jwt.domain.system.Authority;
import com.example.jwt.domain.system.Department;
import com.example.jwt.service.AuthorityService;
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
@Api(value = "AuthorityRestController", description = "authority API")
public class AuthorityRestController {

    @Autowired
    private AuthorityService authorityService;

    @PostMapping("/roles")
    public ResponseEntity<Authority> createAuthority(@Valid @RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authority);

        Authority newAuthority = authorityService.createAuthority(authority);
        return ResponseEntity.created(new URI("/api/roles/" + authority.getRoleName()))
                .headers(HeaderUtil.createAlert("authorityManagement.created", newAuthority.getRoleName()))
                .body(newAuthority);
    }

    @PutMapping("/roles")
    public ResponseEntity<Authority> updateAuthortiy(@Valid @RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to update Authority : {}", authority);

        Authority newAuthority = authorityService.save(authority);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authority.getId().toString()))
                .body(newAuthority);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Authority>> getAllAuthorities() throws URISyntaxException {
        log.debug("REST request to get Authority : {}");
        List<Authority> newAuthority = authorityService.getAllAuthorities();
        return new ResponseEntity<>(newAuthority, HttpStatus.OK);
    }

    @GetMapping( "/roles/{id}")
    public ResponseEntity getAuthority(@PathVariable Long id) throws URISyntaxException {
        log.info("REST request to get single Authority : {}",id);
        Optional<Authority> authority = authorityService.getAuthority(id);

        return new ResponseEntity<>(authority, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable Long id) {
        log.debug("REST request to delete Authority : {}", id);
        authorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
