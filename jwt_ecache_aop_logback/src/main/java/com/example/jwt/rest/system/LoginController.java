package com.example.jwt.rest.system;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.LoginLog;
import com.example.jwt.service.LoginLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginLogService loginLogService;


    @GetMapping("/login")
    public ResponseEntity getLoginLog(@PathVariable String username) throws URISyntaxException{

        logger.info("REST request to save Menu : {}", username);


        LoginLog loginLog = loginLogService.findUserLastLogin(username);
        return new ResponseEntity<>(loginLog, HttpStatus.OK);
    }

//    @RequestMapping("/login.html")
//    public String index() {
//        return "/api/login/index";
//    }



}