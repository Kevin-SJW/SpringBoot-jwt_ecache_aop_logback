package com.example.jwt.system;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.LoginLog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

    /**
     * 根据用户名查询 用户登录记录
     *
     * @param username 用户名
     */
    List<LoginLog> findDistinctTop2ByUsernameOrderByTimeDesc(String username);

}

