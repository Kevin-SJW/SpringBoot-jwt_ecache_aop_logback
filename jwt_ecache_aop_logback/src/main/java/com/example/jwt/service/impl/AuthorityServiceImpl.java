package com.example.jwt.service.impl;

import com.example.jwt.constants.AuthoritiesConstants;
import com.example.jwt.domain.system.Authority;
import com.example.jwt.domain.system.Department;
import com.example.jwt.service.AuthorityService;
import com.example.jwt.system.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).get();
    }

    @Override
    public Authority createAuthority(Authority authority) {

        Authority newAuthority = new Authority();

        newAuthority.setRoleName(authority.getRoleName());
        newAuthority.setRoleKey(authority.getRoleKey());
        newAuthority.setRoleSort(authority.getRoleSort());
        newAuthority.setEnabled(authority.getEnabled());
        newAuthority.setIsDeleted(AuthoritiesConstants.Authorities_NOT_DELETED);
        newAuthority.setRemarks(authority.getRemarks());
        newAuthority.setMenus(authority.getMenus());

        return authorityRepository.save(newAuthority);
    }
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Authority> getAuthority(Long id) {

        Optional<Authority> authority = authorityRepository.findById(id);
        return authority;

    }

    @Override
    public void delete(Long id) {
       authorityRepository.deleteById(id);

    }
}
