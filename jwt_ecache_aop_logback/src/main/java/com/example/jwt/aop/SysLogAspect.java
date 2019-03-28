package com.example.jwt.aop;

import com.alibaba.fastjson.JSON;
import com.example.jwt.domain.annotations.LoginLogs;
import com.example.jwt.domain.system.SysLog;
import com.example.jwt.service.SysLogService;
import com.example.jwt.utils.IpUtils;
import com.example.jwt.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    HttpServletRequest request;

    private final Logger logger = Logger.getLogger(SysLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //定义切点 @Pointcut

    /**
     * 在注解的位置切入代码
     *
     */
    @Pointcut("@annotation(com.example.jwt.domain.annotations.ControllerLogs)")
    public void controllerAspect() {
    }

    /**
     * 切面 配置通知
     *
     * @param joinPoint
     */

    @Before("controllerAspect()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面");
        /**
         * 保存日志
         */

        /*记录当前时间*/
        String startTime;
        /*设置日期格式*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startTime = df.format(new Date());
        System.out.println(startTime);
        SysLog sysLog = new SysLog();


        /*从切面织入点处通过反射机制获取织入点处的方法*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        /*获取切入点所在的方法*/
        Method method = signature.getMethod();

        /*获取操作*/
       LoginLogs loginLogs = method.getAnnotation(LoginLogs.class);
        if (loginLogs != null) {
            String value = loginLogs.value();
            /*保存获取的操作*/
            sysLog.setOperation(value);

        }

        /*获取用户名*/
        Principal principal = request.getUserPrincipal();
        String username = principal == null ? "" : principal.getName();
        sysLog.setUsername(username);
        //类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        /*获取请求的参数*/
        Object[] args = joinPoint.getArgs();
        /*将参数所在的数组转换成json*/
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        /*获取日期*/
        sysLog.setCreateDate(new Date());
        /*获取用户名*/
        sysLog.setUsername(SecurityUtils.getCurrentUserLogin().get());
        /*获取用户ip地址*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setIp(IpUtils.getRemoteAddr(request));

        /*记录下请求内容*/
        logger.info("用户名username : " + username);
        logger.info("用户ip : " + IpUtils.getRemoteAddr(request));
        logger.info("操作operation : " + loginLogs.value());
        logger.info("类名className : " + className);
        logger.info("方法名methodName : " + methodName);
        logger.info("请求参数params : " + params);
        logger.info("日期createDate : " + new Date());
        /*调用service保存SysLog实体类到数据库*/
        sysLogService.save(sysLog);
    }
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) throws Throwable {
        String endTime;
        /*处理完请求，记录请求返回的对象*/
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

}


