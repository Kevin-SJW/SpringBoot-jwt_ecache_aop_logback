package com.example.jwt.rest.system;

//import antlr.ASTNULLType;
//import com.example.jwt.domain.system.position;
import com.example.jwt.domain.system.Position;
import com.example.jwt.system.PositionRepository;
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
public class PositionRestServiceTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PositionRepository positionRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void postCreatePositionSuccessfully1() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("postKey", "ceo");
        map.put("postName", "董事长");
        map.put("postSort", "1");
        map.put("enabled", true);
        MvcResult result = mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdatePositionSuccessfully1() throws Exception {
        List list = new ArrayList();
        List<Position> positionList = positionRepository.findAll();
        for (Position position : positionList) {
            long id = position.getId();
            list.add(id);
            System.out.println(list);
            positionRepository.save(position);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("postKey", "ceo");
            map.put("postName", "董事长");
            map.put("postSort", "1");
            map.put("enabled", true);
            MvcResult result = mockMvc.perform(put("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void getGetAllPositionSuccessfully1() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("postKey", "ceo");
        map.put("postName", "董事长");
        map.put("postSort", "1");
        map.put("enabled", true);
        MvcResult result = mockMvc.perform(get("/api/posts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeletePositionSuccessfully1() throws Exception {
        List list =new ArrayList();
        List<Position> positionList = positionRepository.findAll();
        for (Position position : positionList) {
            Long id = position.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
//        map.put("postKey", "ceo");
//        map.put("postName", "董事长");
//        map.put("postSort", "1");
//        map.put("enabled", true);
        for(Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/posts/" + id)
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }


    @Test
    public void postCreatePositionSuccessfully2() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("postKey", "pm");
        map.put("postName", "项目经理");
        map.put("postSort", "2");
        map.put("enabled", true);
        MvcResult result = mockMvc.perform(post("/api/posts")
            .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdatePositionSuccessfully2() throws Exception {
        List list = new ArrayList();
        List<Position> positionList = positionRepository.findAll();
        for (Position position : positionList) {
            long id = position.getId();
            list.add(id);
            System.out.println(list);
            positionRepository.save(position);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("postKey", "pm");
            map.put("postName", "项目经理");
            map.put("postSort", "2");
            map.put("enabled", true);
            MvcResult result = mockMvc.perform(put("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
    @Test
    public void getGetAllPositionSuccessfully2() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("postKey", "pm");
        map.put("postName", "项目经理");
        map.put("postSort", "2");
        map.put("enabled", true);
        MvcResult result = mockMvc.perform(get("/api/posts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeletePositionSuccessfully2() throws Exception {
        List list =new ArrayList();
        List<Position> positionList = positionRepository.findAll();
        for (Position position : positionList) {
            Long id = position.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
//        map.put("postKey", "pm");
//        map.put("postName", "项目经理");
//        map.put("postSort", "2");
//        map.put("enabled", true);
        for(Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/posts/" + id)
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void postCreatePositionSuccessfully3() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("postKey", "dev");
        map.put("postName", "软件开发");
        map.put("postSort", "3");
        map.put("enabled", true);
        MvcResult result = mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void putUpdatePositionSuccessfully3() throws Exception {
        List list = new ArrayList();
        List<Position> positionList = positionRepository.findAll();
        for (Position position : positionList) {
            long id = position.getId();
            list.add(id);
            System.out.println(list);
            positionRepository.save(position);
        }
        Map<String, Object> map = new HashMap<>();
        for (Object id : list) {

            map.put("id", id);
            map.put("postKey", "dev");
            map.put("postName", "软件开发");
            map.put("postSort", "3");
            map.put("enabled", true);
            MvcResult result = mockMvc.perform(put("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    public void getGetAllPositionSuccessfully3() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("postKey", "dev");
        map.put("postName", "软件开发");
        map.put("postSort", "3");
        map.put("enabled", true);
        MvcResult result = mockMvc.perform(get("/api/posts")
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void deleteDeletePositionSuccessfully3() throws Exception {
        List list =new ArrayList();
        List<Position> positionList = positionRepository.findAll();
        for (Position position : positionList) {
            Long id = position.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        Map<String, Object> map = new HashMap<>();
//        map.put("postKey", "dev");
//        map.put("postName", "软件开发");
//        map.put("postSort", "3");
//        map.put("enabled", true);
        for(Object id : list) {
            MvcResult result = mockMvc.perform(delete("/api/posts/" + id)
                    .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                    .andExpect(status().is2xxSuccessful())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
}
