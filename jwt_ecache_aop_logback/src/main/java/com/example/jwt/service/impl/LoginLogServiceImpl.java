package com.example.jwt.service.impl;

import com.example.jwt.domain.system.LoginLog;
import com.example.jwt.service.LoginLogService;
import com.example.jwt.system.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

//import javax.activity.ActivityCompletedException;


//import java.util.stream.Collectors;




@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogRepository loginLogRepository;

    @Override
    public LoginLog findUserLastLogin(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        LoginLog loginLog;
        List<LoginLog> list = loginLogRepository.findDistinctTop2ByUsernameOrderByTimeDesc(username);
        if (list.size() == 2) {
            loginLog = list.get(1);
        } else if (list.size() == 1) {
            loginLog = list.get(0);
        } else {
            loginLog = null;
        }
        return loginLog;
    }

    @Override
    public LoginLog save(LoginLog loginLog) {
        if (loginLog == null) {
            return null;
        }
        return loginLogRepository.save(loginLog);
    }
}
