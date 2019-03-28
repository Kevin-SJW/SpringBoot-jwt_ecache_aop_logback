package com.example.jwt.rest.system;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.example.jwt.domain.annotations.LoginLogs;
import com.example.jwt.domain.annotations.LoginType;
import com.example.jwt.domain.system.Department;
import com.example.jwt.service.DepartmentService;
import com.example.jwt.system.DepartmentRepository;
import com.example.jwt.utils.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.*;
//import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.sql.Timestamp;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

//import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;


@RestController
@RequestMapping("/api")
@Api(value = "DepartmentRestController", description = "department API")
public class DepartmentRestController  {

    @Autowired
    private DepartmentService departmentService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CacheManager cacheManager;


    @ApiOperation(value = "create department", notes = "create department",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dept",paramType = "body",value = "",
                    examples = @Example(value = {@ExampleProperty(value = " ", mediaType = "application/json")})),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "the ResponseEntity with status 201 (Created) and with body the new dept " ),
            @ApiResponse(code = 400, message = "with status 400 (Bad Request) if the dept has already an ID"),

    })
    @PostMapping("/depts")
    @LoginLogs(type = LoginType.controller, value = "创建部门")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department dept ) throws URISyntaxException {

        Department newDept = departmentService.createDept(dept);
        System.out.println("进行缓存：" +" 直接写入数据库，ID为：" + newDept.getId());
        logger.info("REST request to create Department : {}", newDept.getId());


        return ResponseEntity.created(new URI("/api/depts/" + newDept.getDeptName()))
                .headers(HeaderUtil.createAlert("userManagement.created", newDept.getDeptName()))
                .body(newDept);

    }


    @ApiOperation(value = "update department", notes = "update department",
            httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dept",paramType = "body",value = " ",
                    examples = @Example(value = {@ExampleProperty(value = " ", mediaType = "application/json")})),

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK) and with body the updated dept" ),
            @ApiResponse(code = 400, message = "with status 400 (Bad Request) if the dept is not valid"),
            @ApiResponse(code = 500, message = "with status 500 (Internal Server Error) if the dept couldn't be updated"),

    })


    @PutMapping("/depts")
    @LoginLogs(type = LoginType.controller, value = "更新部门")
    public ResponseEntity updateDepartment(@Valid @RequestBody Department dept) throws URISyntaxException {
        logger.info("REST request to update Department : {}", dept.getId());

        Department newDept = departmentService.save(dept);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dept.getId().toString()))
                .body(newDept);
    }


    @ApiOperation(value = "get all department", notes = "get all department",
            httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK) and the list of department in body" ),

    })

    @GetMapping( "/depts")
    @LoginLogs(type = LoginType.controller, value = "获取所有部门")
    public ResponseEntity getAllDepartment() throws URISyntaxException {
        logger.info("REST request to get All Department : {}");
        List<Department> newDept = departmentService.getAllDepartments();

        return new ResponseEntity<>(newDept, HttpStatus.OK);
    }

    @ApiOperation(value = "get department", notes = "get department",
            httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK) and the single department in body " ),

    })
    @GetMapping( "/depts/{id}")
    @LoginLogs(type = LoginType.controller, value = "获取指定部门")
    public ResponseEntity getDepartment(@PathVariable Long id) throws URISyntaxException {
        logger.info("REST request to get single Department : {}",id);
        Optional<Department> newDept = departmentService.getDepartment(id);

        System.out.println(cacheManager.toString());


        return new ResponseEntity<>(newDept, HttpStatus.OK);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(newDept));
    }

    @ApiOperation(value = "delete department", notes = "delete department",
            httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the ResponseEntity with status 200 (OK)"),

    })
    @DeleteMapping("/depts/{id}")
    @LoginLogs(type = LoginType.controller, value = "删除部门")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        logger.info("REST request to delete Department : {}", id);
        departmentService.delete(id);
        System.out.println("缓存删除");
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
