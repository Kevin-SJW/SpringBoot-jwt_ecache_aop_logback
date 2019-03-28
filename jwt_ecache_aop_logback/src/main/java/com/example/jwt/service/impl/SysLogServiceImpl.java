package com.example.jwt.service.impl;

import com.example.jwt.domain.annotations.LoginLogs;
import com.example.jwt.domain.annotations.LoginType;
import com.example.jwt.domain.system.SysLog;
import com.example.jwt.service.SysLogService;
import com.example.jwt.system.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;

    @LoginLogs(type = LoginType.service, value = "保存")
    @Override
    public SysLog save(SysLog syslog) {
        if (syslog == null) {
            return null;
        }
        return sysLogRepository.save(syslog);
    }
}
