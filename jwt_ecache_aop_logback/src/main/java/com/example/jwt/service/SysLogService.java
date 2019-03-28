package com.example.jwt.service;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.SysLog;

import java.util.List;
import java.util.Optional;


public interface SysLogService {


    /**保存信息
     *
     * @param syslog
     * @return
     */
     SysLog save(SysLog syslog) ;

//    List<Department> getAllDepartments();
//
//    Optional<Department> getDepartment(Long id);

//    void delete(Long id);








}
