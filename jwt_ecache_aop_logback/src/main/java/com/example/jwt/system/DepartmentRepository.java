package com.example.jwt.system;

import com.example.jwt.domain.system.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



@CacheConfig(cacheNames = "department")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Cacheable
    Department findByDeptKey(String department);

}

