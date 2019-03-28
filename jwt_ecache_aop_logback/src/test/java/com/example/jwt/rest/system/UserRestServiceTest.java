package com.example.jwt.rest.system;

import antlr.ASTNULLType;
import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.User;
import com.example.jwt.service.UserService;
import com.example.jwt.service.dto.UserDTO;
import com.example.jwt.system.AuthorityRepository;
import com.example.jwt.system.DepartmentRepository;
import com.example.jwt.system.PositionRepository;
import com.example.jwt.system.UserRepository;
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
public class UserRestServiceTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void postCreateUserSuccessfully() throws Exception {

        List<String> adminAuthority = Arrays.asList("admin", "user");
        List<String> adminPosition = Arrays.asList("dev");
        String department = "Company";

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("displayName", "Zhao Tian");
            map.put("username", "admin");
            map.put("password", "admin");
            map.put("firstName", "tian");
            map.put("lastName", "zhao");
            map.put("email", "tian.zhao@siemens.com");
            map.put("gender", "M");
            map.put("phone", "88888888");
            map.put("mobile", "18888888888");
            map.put("department", department);
            map.put("positions", adminPosition);
            map.put("authorities", adminAuthority);

            MvcResult result = mockMvc.perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateUserSuccessfully() throws Exception {

        List<String> adminAuthority = Arrays.asList("admin", "user");
        List<String> adminPosition = Arrays.asList("dev");
        String department = "Company";


        List list = new ArrayList();
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
//            user.setId(userDTO.getId());
//
            long id = user.getId();
            list.add(id);
            System.out.println(list);
            userRepository.save(user);
        }
        Map<String, Object> map = new HashMap<>();
//        try {
            for (Object id : list) {

                map.put("id", id);
                map.put("displayName", "Zhao Tian1");
                map.put("username", "admin1");
                map.put("password", "admin");
                map.put("firstName", "tian");
                map.put("lastName", "zhao");
                map.put("email", "tian.zhao@siemens.com");
                map.put("gender", "M");
                map.put("phone", "88888888");
                map.put("mobile", "18888888888");
                map.put("department", department);
                map.put("positions", adminPosition);
                map.put("authorities", adminAuthority);

                MvcResult result = mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    @Test
    public void getGetUserSuccessfully() throws Exception {

        List<String> adminAuthority = Arrays.asList("admin", "user");
        List<String> adminPosition = Arrays.asList("dev");
        String department = "Company";

        Map<String, Object> map = new HashMap<>();
        map.put("displayName", "Zhao Tian");
        map.put("username", "admin");
        map.put("password", "admin");
        map.put("firstName", "tian");
        map.put("lastName", "zhao");
        map.put("email", "tian.zhao@siemens.com");
        map.put("gender", "M");
        map.put("phone", "88888888");
        map.put("mobile", "18888888888");
        map.put("department", department);
        map.put("positions", adminPosition);
        map.put("authorities", adminAuthority);

        MvcResult result = mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteUserSuccessfully() throws Exception {
        List list =new ArrayList();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            Long id = user.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
        List<String> adminAuthority = Arrays.asList("admin", "user");
        List<String> adminPosition = Arrays.asList("dev");
        String department = "Company";

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("displayName", "Zhao Tian");
            map.put("username", "admin");
            map.put("password", "admin");
            map.put("firstName", "tian");
            map.put("lastName", "zhao");
            map.put("email", "tian.zhao@siemens.com");
            map.put("gender", "M");
            map.put("phone", "88888888");
            map.put("mobile", "18888888888");
            map.put("department", department);
            map.put("positions", adminPosition);
            map.put("authorities", adminAuthority);

            for (Object id : list) {
                MvcResult result = mockMvc.perform(delete("/api/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
