package com.example.jwt.rest.system;

import com.example.jwt.constants.AuthoritiesConstants;
import com.example.jwt.constants.MenuConstants;
import com.example.jwt.domain.system.Menu;
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
public class MenuRestServiceTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MenuRepository menuRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void postCreateMenuSuccessfully1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "系统管理");
        map.put("parentId", 0);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.MENU);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "系统管理目录");
        MvcResult result = mockMvc.perform(post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateMenuSuccessfully1() throws Exception {

        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            long id = menu.getId();
            list.add(id);
            System.out.println(list);
            menuRepository.save(menu);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("menuName", "系统管理");
            map.put("parentId", 0);
            map.put("menuKey", AuthoritiesConstants.ADMIN);
            map.put("menuType", MenuConstants.MENU);
            map.put("enabled", true);
            map.put("isDeleted", "0");
            map.put("remarks", "系统管理目录");
            MvcResult result = mockMvc.perform(put("/api/menus")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void getGetMenuSuccessfully1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "系统管理");
        map.put("parentId", 0);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.MENU);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "系统管理目录");
        MvcResult result = mockMvc.perform(get("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteMenuSuccessfully1() throws Exception {
        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            Long id = menu.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "系统管理");
        map.put("parentId", 0);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.MENU);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "系统管理目录");
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/menus/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }



    @Test
    public void postCreateMenuSuccessfully2() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "用户管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.USER);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "用户管理目录");
        MvcResult result = mockMvc.perform(post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateMenuSuccessfully2() throws Exception {

        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            long id = menu.getId();
            list.add(id);
            System.out.println(list);
            menuRepository.save(menu);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("menuName", "用户管理");
            map.put("parentId", 1);
            map.put("menuKey", AuthoritiesConstants.USER);
            map.put("menuType", MenuConstants.CATALOG);
            map.put("enabled", true);
            map.put("isDeleted", "0");
            map.put("remarks", "用户管理目录");
            MvcResult result = mockMvc.perform(put("/api/menus")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void getGetMenuSuccessfully2() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "用户管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.USER);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "用户管理目录");
        MvcResult result = mockMvc.perform(get("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteMenuSuccessfully2() throws Exception {
        List list =new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            Long id = menu.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "用户管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.USER);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "用户管理目录");
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/menus/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void postCreateMenuSuccessfully3() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "角色管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "角色管理目录");
        MvcResult result = mockMvc.perform(post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateMenuSuccessfully3() throws Exception {

        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            long id = menu.getId();
            list.add(id);
            System.out.println(list);
            menuRepository.save(menu);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("menuName", "角色管理");
            map.put("parentId", 1);
            map.put("menuKey", AuthoritiesConstants.ADMIN);
            map.put("menuType", MenuConstants.CATALOG);
            map.put("enabled", true);
            map.put("isDeleted", "0");
            map.put("remarks", "角色管理目录");
            MvcResult result = mockMvc.perform(put("/api/menus")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
    @Test
    public void getGetMenuSuccessfully3() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "角色管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "角色管理目录");
        MvcResult result = mockMvc.perform(get("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteMenuSuccessfully3() throws Exception {
        List list =new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            Long id = menu.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "角色管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "角色管理目录");
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/menus/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void postCreateMenuSuccessfully4() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "菜单管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "菜单管理目录");
        MvcResult result = mockMvc.perform(post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateMenuSuccessfully4() throws Exception {

        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            long id = menu.getId();
            list.add(id);
            System.out.println(list);
            menuRepository.save(menu);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("menuName", "菜单管理");
            map.put("parentId", 1);
            map.put("menuKey", AuthoritiesConstants.ADMIN);
            map.put("menuType", MenuConstants.CATALOG);
            map.put("enabled", true);
            map.put("isDeleted", "0");
            map.put("remarks", "菜单管理目录");
            MvcResult result = mockMvc.perform(put("/api/menus")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void getGetMenuSuccessfully4() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "菜单管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "菜单管理目录");
        MvcResult result = mockMvc.perform(get("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteMenuSuccessfully4() throws Exception {
        List list =new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            Long id = menu.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "菜单管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "菜单管理目录");
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/menus/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void postCreateMenuSuccessfully5() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "部门管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "部门管理目录");
        MvcResult result = mockMvc.perform(post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateMenuSuccessfully5() throws Exception {

        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            long id = menu.getId();
            list.add(id);
            System.out.println(list);
            menuRepository.save(menu);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("menuName", "部门管理");
            map.put("parentId", 1);
            map.put("menuKey", AuthoritiesConstants.ADMIN);
            map.put("menuType", MenuConstants.CATALOG);
            map.put("enabled", true);
            map.put("isDeleted", "0");
            map.put("remarks", "部门管理目录");
            MvcResult result = mockMvc.perform(put("/api/menus")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
    @Test
    public void getGetMenuSuccessfully5() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "部门管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "部门管理目录");
        MvcResult result = mockMvc.perform(get("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteMenuSuccessfully5() throws Exception {
        List list =new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            Long id = menu.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "部门管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "部门管理目录");
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/menus/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void postCreateMenuSuccessfully6() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "职位管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "职位管理目录");
        MvcResult result = mockMvc.perform(post("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdateMenuSuccessfully6() throws Exception {

        List list = new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            long id = menu.getId();
            list.add(id);
            System.out.println(list);
            menuRepository.save(menu);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("menuName", "职位管理");
            map.put("parentId", 1);
            map.put("menuKey", AuthoritiesConstants.ADMIN);
            map.put("menuType", MenuConstants.CATALOG);
            map.put("enabled", true);
            map.put("isDeleted", "0");
            map.put("remarks", "职位管理目录");

            MvcResult result = mockMvc.perform(put("/api/menus")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void getGetMenuSuccessfully6() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "职位管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "职位管理目录");

        MvcResult result = mockMvc.perform(get("/api/menus")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeleteMenuSuccessfully6() throws Exception {
        List list =new ArrayList();
        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            Long id = menu.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuName", "职位管理");
        map.put("parentId", 1);
        map.put("menuKey", AuthoritiesConstants.ADMIN);
        map.put("menuType", MenuConstants.CATALOG);
        map.put("enabled", true);
        map.put("isDeleted", "0");
        map.put("remarks", "职位管理目录");
        for (Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/menus/" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

}
