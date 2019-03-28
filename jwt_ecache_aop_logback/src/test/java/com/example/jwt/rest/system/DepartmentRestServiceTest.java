package com.example.jwt.rest.system;


import com.example.jwt.domain.system.Department;
import com.example.jwt.service.DepartmentService;
import com.example.jwt.system.DepartmentRepository;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRestServiceTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void postCreateDepartmentSuccessfully() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("deptName", "Siemens");
        map.put("deptKey", "Company");
        map.put("parentId", 0);
        map.put("deptSort", "1");
        map.put("leader", "Siemens");
        map.put("mobile", "Siemens");
        map.put("email", "Siemens@siemens.com");
        map.put("enabled", true);
        map.put("remarks", "Siemens");
        MvcResult result = mockMvc.perform(post("/api/depts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateDepartmentSuccessfully() throws Exception {

        List list = new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList)
        {
            long id = department.getId();
            list.add(id);
            System.out.println(list);
            departmentRepository.save(department);
        }
        Map<String, Object> map = new HashMap<>();
        for(Object id:list) {

            map.put("id", id);
            map.put("deptName", "Siemens1");
            map.put("deptKey", "Company");
            map.put("parentId", 0);
            map.put("deptSort", "1");
            map.put("leader", "Siemens");
            map.put("mobile", "Siemens");
            map.put("email", "Siemens@siemens.com");
            map.put("enabled", true);
            map.put("remarks", "Siemens");
//        departmentRepository.save(map);

            MvcResult result = mockMvc.perform(put("/api/depts" )
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }


    @Test
    public void getGetAllDepartmentSuccessfully() throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("deptName", "Siemens");
        map.put("deptKey", "Company");
        map.put("parentId", 0);
        map.put("deptSort", "1");
        map.put("leader", "Siemens");
        map.put("mobile", "Siemens");
        map.put("email", "Siemens@siemens.com");
        map.put("enabled", true);
        map.put("remarks", "Siemens");
        MvcResult result = mockMvc.perform(get("/api/depts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void getGetDepartmentSuccessfully() throws Exception {
//        Long id;
//        Optional<Department> department =  departmentRepository.findById();

        List list =new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            Long id = department.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        for(Object id:list) {
            map.put("id", id);
            map.put("deptName", "Siemens");
            map.put("deptKey", "Company");
            map.put("parentId", 0);
            map.put("deptSort", "1");
            map.put("leader", "Siemens");
            map.put("mobile", "Siemens");
            map.put("email", "Siemens@siemens.com");
            map.put("enabled", true);
            map.put("remarks", "Siemens");
            MvcResult result = mockMvc.perform(get("/api/depts/" + id)
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteDepartmentSuccessfully() throws Exception {
        List list =new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            Long id = department.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
            Map<String, Object> map = new HashMap<>();
            map.put("deptName", "Siemens");
            map.put("deptKey", "Company");
            map.put("parentId", 0);
            map.put("deptSort", "1");
            map.put("leader", "Siemens");
            map.put("mobile", "Siemens");
            map.put("email", "Siemens@siemens.com");
            map.put("enabled", true);
            map.put("remarks", "Siemens");

        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/depts/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }

        }

}
