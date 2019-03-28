package com.example.jwt.system;

import com.example.jwt.domain.system.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu, Long> {
}
