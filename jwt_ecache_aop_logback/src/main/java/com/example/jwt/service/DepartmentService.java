package com.example.jwt.service;

import com.example.jwt.domain.system.Department;

import java.util.List;
import java.util.Optional;


public interface DepartmentService {
    Department createDept(Department dept);


    Department save(Department dept);

    List<Department> getAllDepartments();

    Optional<Department> getDepartment(Long id);

    void delete(Long id);









}
