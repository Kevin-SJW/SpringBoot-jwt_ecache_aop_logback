package com.example.jwt.service;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.LoginLog;

import java.util.List;
import java.util.Optional;


public interface LoginLogService {
    /**
     * 查询用户上次登录
     *
     * @param username 用户名
     */
    LoginLog findUserLastLogin(String username);

    /**
     * 保存
     *
     * @param loginLog record

     */
    LoginLog save(LoginLog loginLog);
}








