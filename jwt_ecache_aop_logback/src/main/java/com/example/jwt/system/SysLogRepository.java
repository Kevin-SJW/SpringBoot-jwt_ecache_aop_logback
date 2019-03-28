package com.example.jwt.system;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.SysLog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SysLogRepository extends JpaRepository<SysLog, Long> {




//    Department getOne(Long id);
}

