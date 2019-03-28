package com.example.jwt.rest.system;

import antlr.ASTNULLType;
import com.example.jwt.domain.system.Authority;
import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.Menu;
import com.example.jwt.system.AuthorityRepository;
import com.example.jwt.system.MenuRepository;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityRestServiceTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void postCreateAuthoritySuccessfully1() throws Exception {

        List<Menu> menus = menuRepository.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "管理员");
        map.put("roleKey", "admin");
        map.put("roleSort", "2");
        map.put("enabled", true);
        map.put("remarks", "admin");
        map.put("menus", menus);
        MvcResult result = mockMvc.perform(post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateAuthoritySuccessfully1() throws Exception {
        List list = new ArrayList();
        List<Authority> authorityList = authorityRepository.findAll();
        for (Authority authority : authorityList) {
            long id = authority.getId();
            list.add(id);
            System.out.println(list);
            authorityRepository.save(authority);
        }
        List<Menu> menus = menuRepository.findAll();
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {
//            List<Menu> menus = menuRepository.findAll();

            map.put("id", id);
            map.put("roleName", "管理员");
            map.put("roleKey", "admin");
            map.put("roleSort", "1");
            map.put("enabled", true);
            map.put("remarks", "admin");
            map.put("menus", menus);
            MvcResult result = mockMvc.perform(put("/api/roles")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
    @Test
    public void getGetAuthoritySuccessfully1() throws Exception {

        List<Menu> menus = menuRepository.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "管理员");
        map.put("roleKey", "admin");
        map.put("roleSort", "1");
        map.put("enabled", true);
        map.put("remarks", "admin");
        map.put("menus", menus);
        MvcResult result = mockMvc.perform(get("/api/roles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteAuthoritySuccessfully1() throws Exception {
        List list =new ArrayList();
        List<Authority> authorityList = authorityRepository.findAll();
        for (Authority authority : authorityList) {
            Long id = authority.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        List<Menu> menus = menuRepository.findAll();
        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "管理员");
        map.put("roleKey", "admin");
        map.put("roleSort", "1");
        map.put("enabled", true);
        map.put("remarks", "admin");
        map.put("menus", menus);
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/roles/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }

    }

    @Test
    public void postCreateAuthoritySuccessfully2() throws Exception {

        List<Menu> menus = menuRepository.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "普通角色");
        map.put("roleKey", "user");
        map.put("roleSort", "2");
        map.put("enabled", true);
        map.put("remarks", "common role");
        map.put("menus", menus);
        MvcResult result = mockMvc.perform(post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @SuppressWarnings(value = "unchecked")
    public void putUpdateAuthoritySuccessfully2() throws Exception {
        List list = new ArrayList();
        List<Authority> authorityList = authorityRepository.findAll();
        for (Authority authority : authorityList) {
            long id = authority.getId();
            list.add(id);
            System.out.println(list);
            authorityRepository.save(authority);
        }
        List<Menu> menus = menuRepository.findAll();
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {


            map.put("id", id);
            map.put("roleName", "普通角色");
            map.put("roleKey", "user");
            map.put("roleSort", "2");
            map.put("enabled", true);
            map.put("remarks", "common role");
            map.put("menus", menus);
            MvcResult result = mockMvc.perform(put("/api/roles")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
    @Test
    public void getGetAuthoritySuccessfully2() throws Exception {

        List<Menu> menus = menuRepository.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "普通角色");
        map.put("roleKey", "user");
        map.put("roleSort", "2");
        map.put("enabled", true);
        map.put("remarks", "common role");
        map.put("menus", menus);
        MvcResult result = mockMvc.perform(get("/api/roles")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteAuthoritySuccessfully2() throws Exception {
        List list =new ArrayList();
        List<Authority> authorityList = authorityRepository.findAll();
        for (Authority authority : authorityList) {
            Long id = authority.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        List<Menu> menus = menuRepository.findAll();
        System.out.println(list);

        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "普通角色");
        map.put("roleKey", "user");
        map.put("roleSort", "2");
        map.put("enabled", true);
        map.put("remarks", "common role");
        map.put("menus", menus);
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/roles/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }

    }
}
