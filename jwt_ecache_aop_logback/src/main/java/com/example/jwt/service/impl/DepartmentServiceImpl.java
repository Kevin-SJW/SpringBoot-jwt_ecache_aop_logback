package com.example.jwt.service.impl;

import com.example.jwt.constants.DepartmentConstants;
import com.example.jwt.domain.system.Department;
import com.example.jwt.service.DepartmentService;

import com.example.jwt.system.DepartmentRepository;

import jdk.nashorn.internal.ir.annotations.Reference;
import net.sf.ehcache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import javax.activity.ActivityCompletedException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


//import java.util.stream.Collectors;




@CacheConfig(cacheNames = {"department"})
@Service
@Repository
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private static final String CACHE_KEY = "'dept'";

    // value的值和ehcache.xml中的配置保持一致

    @Override
    @CacheEvict(value = "department", key = CACHE_KEY)
    public Department createDept(Department dept) throws CacheException {

        Department newDepartment = new Department();
        newDepartment.setParent_id(dept.getParent_id());
        newDepartment.setDeptName(dept.getDeptName());
        newDepartment.setDeptKey(dept.getDeptKey());
        newDepartment.setDeptSort(dept.getDeptSort());
        newDepartment.setLeader(dept.getLeader());
        newDepartment.setMobile(dept.getMobile());
        newDepartment.setEmail(dept.getEmail());
        newDepartment.setEnabled(dept.getEnabled());
        newDepartment.setIdDeleted(DepartmentConstants.Department_NOT_DELETED);
        newDepartment.setRemarks(dept.getRemarks());

        return departmentRepository.save(newDepartment);
    }

    @Override
    @CachePut(value = "department",key = "'department_' + #dept.getId()")
    @CacheEvict(value = "department",key = CACHE_KEY,allEntries = true)
    public Department save(Department dept) throws CacheException {

        Department newDepartment = new Department();
        if (newDepartment == null) {
            throw new CacheException("Not Find");
        }
        newDepartment.setId(dept.getId());
        System.out.println("更新数据库，并清空缓存，ID为：" + dept.getId());
        newDepartment.setParent_id(dept.getParent_id());
        newDepartment.setDeptName(dept.getDeptName());
        newDepartment.setDeptKey(dept.getDeptKey());
        newDepartment.setDeptSort(dept.getDeptSort());
        newDepartment.setLeader(dept.getLeader());
        newDepartment.setMobile(dept.getMobile());
        newDepartment.setEmail(dept.getEmail());
        newDepartment.setEnabled(dept.getEnabled());
        newDepartment.setIdDeleted(DepartmentConstants.Department_NOT_DELETED);
        newDepartment.setRemarks(dept.getRemarks());
        return departmentRepository.save(dept);
    }


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "department",key = CACHE_KEY)
    public List<Department> getAllDepartments() {

        System.out.println("为getAllDepartment"  + "数据做了缓存");
        return departmentRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "department", key = "'department_' + #id")
    public Optional<Department> getDepartment(Long id) {

        Optional<Department> dept = Optional.of(departmentRepository.findById(id).get());
        System.out.println("缓存找到，直接读取缓存，ID为：" + id);
        return dept;

    }

    @Override
    @CacheEvict(value = "department",key = "'department_' + #id",allEntries = true)
    public void delete(Long id) {
        departmentRepository.deleteById(id);
        System.out.println("删除缓存，并删除数据库数据，ID为：" + id);

    }
}
