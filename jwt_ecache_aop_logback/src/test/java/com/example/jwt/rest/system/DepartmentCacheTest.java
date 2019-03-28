package com.example.jwt.rest.system;

import antlr.build.Tool;
import com.example.jwt.constants.DepartmentConstants;
import com.example.jwt.domain.system.Department;
import com.example.jwt.service.DepartmentService;
import com.example.jwt.system.DepartmentRepository;
import javafx.application.Application;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.xml.datatype.DatatypeFactory.newInstance;
import static jdk.nashorn.internal.objects.NativeMath.max;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentCacheTest {
    private Logger logger = LoggerFactory.getLogger(DepartmentCacheTest.class);



    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private Department department;


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//    long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
    String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
//
//    long timestamp = new Timestamp(System.currentTimeMillis()).getTime();

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    /*map与实体对象的转换*/
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();


            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }



   /* 测试CreateDepartment的方法
    逻辑：首先post数据,再次get获取，缓存有效期内得到缓存内的的数据
    超过缓存有效时间，则再次查询数据库，获取相应数据*/
    @Test
    public void testCreateDepartment() throws Exception {


        System.out.println("====================createDepartment方法测试====================");
        Department newDepartment = new Department();
        newDepartment.setParent_id(100L);
        newDepartment.setDeptName("市场部门");
        newDepartment.setDeptSort("2");
        newDepartment.setLeader("陈云");
        newDepartment.setMobile("15802598732");
        newDepartment.setEmail("chenyun@163.com");
        newDepartment.setEnabled(true);
        newDepartment.setIdDeleted("0");
        newDepartment.setDeptKey("Marketing Department");
        newDepartment.setRemarks("Marketing Department");
        newDepartment.setCreatedBy("system");
        departmentRepository.save(newDepartment);
        System.out.println(timestamp);
        /*save后返回一个对象，进而获取主键id*/
        long id = newDepartment.getId();

        /*第一次查询*/
        System.out.println(departmentService.getDepartment(id));
        System.out.println(timestamp);
        Thread.sleep(6000);

        /*通过缓存查询*/
        System.out.println(departmentService.getDepartment(id));
        System.out.println(timestamp);
    }




    /* 测试updateDepartment的方法
    逻辑：首先get操作，获取数据，然后进行更新db操作，清楚缓存，
    再进行查询操作，缓存因清除，所以查询db，此时获取的数据应该是更新后的数据，
    下一次再查询，在缓存有效期内，则查缓存，在缓存有效期外，则再次查询db*/
    @Test
    public void testUpdate() throws InterruptedException{
        System.out.println("====================updateDepartment方法测试====================");
        Department newDepartment = new Department();
        List list =new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            Long id = department.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
//         departmentRepository.save(newDepartment);
        for(Object id:list) {
            /*第一次查询，此处获取的id将指定接下来更新的相应id的记录*/
        /*更新数据*/
            newDepartment.setId((Long) id);
            System.out.println(departmentService.getDepartment((Long) id));
            System.out.println(timestamp);
            newDepartment.setParent_id(100L);
            newDepartment.setDeptName("市场部门");
            newDepartment.setDeptSort("1");
            newDepartment.setLeader("陈云");
            newDepartment.setMobile("15802598732");
            newDepartment.setEmail("chenyun@163.com");
            newDepartment.setEnabled(true);
            newDepartment.setIdDeleted("0");
            newDepartment.setDeptKey("Marketing Department");
            newDepartment.setRemarks("Marketing Department");
            newDepartment.setCreatedBy("system");
            departmentService.save(newDepartment);

        /*第二次查询，查数据库*/
        System.out.println(departmentService.getDepartment((Long) id));
        System.out.println(timestamp);
        Thread.sleep(4000);
        /*第三次查询，查缓存*/
        System.out.println(departmentService.getDepartment((Long) id));
        System.out.println(timestamp);
        }

    }


    /* 测试getDepartment的方法
    逻辑：设置两次时间，一次在缓存失效期内，一次在失效期外，
    前者一直都查缓存，后者则在失效后对db进行查询操作*/
    @Test
    public void testgetDepartment() throws InterruptedException {
        System.out.println("====================getDepartment方法测试====================");
        List list =new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            Long id = department.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
        Department newDepartment = new Department();
        for(Object id:list) {
            newDepartment.setId((Long) id);
            newDepartment.setParent_id(100L);
            newDepartment.setDeptName("市场部门");
            newDepartment.setDeptSort("1");
            newDepartment.setLeader("陈云");
            newDepartment.setMobile("15802598732");
            newDepartment.setEmail("chenyun@163.com");
            newDepartment.setEnabled(true);
            newDepartment.setIdDeleted("0");
            newDepartment.setDeptKey("Marketing Department");
            newDepartment.setRemarks("Marketing Department");
            newDepartment.setCreatedBy("system");
            departmentService.getDepartment((Long) id); // 模拟从数据库中查询数据
            System.out.println(date);
            Thread.sleep(3000);
            departmentService.getDepartment((Long) id);
            System.out.println(date);
            Thread.sleep(1000);
        }

    }

    /* 测试getAllDepartment的方法
    逻辑：设置两次时间，一次在缓存失效期内，一次在失效期外，
    前者一直都查缓存，后者则在失效后对db进行查询操作*/
    @Test
    public void testgetAllDepartments() throws InterruptedException{
        System.out.println("====================getAllDepartment方法测试====================");
        departmentService.getAllDepartments();
        System.out.println(date);
        Thread.sleep(3000);
        departmentService.getAllDepartments();
        System.out.println(date);
        Thread.sleep(3000);
        departmentService.getAllDepartments();
        System.out.println(date);

    }
    /* 测试deleteDepartment的方法
    逻辑：调用方法时，清空缓存，并删除数据库中的数据*/
    @Test
    public void testdelete() throws InterruptedException{
        System.out.println("====================deleteDepartment方法测试====================");
        List list =new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department : departmentList) {
            Long id = department.getId();
            System.out.println("========>" + id);
            list.add(id);
        }
        System.out.println(list);
        for(Object id:list) {

            departmentService.delete((Long) id);


        System.out.println("删除数据同时删除缓存");
        }

    }




}
