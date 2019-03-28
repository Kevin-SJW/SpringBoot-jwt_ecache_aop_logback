package com.example.jwt.aop;

import com.alibaba.fastjson.JSON;
import com.example.jwt.domain.annotations.LoginLogs;
import com.example.jwt.domain.annotations.ServiceLogs;
import com.example.jwt.utils.IpUtils;
import com.example.jwt.utils.UserAgentUtils;
import com.example.jwt.utils.StringUtils;
import eu.bitwalker.useragentutils.UserAgent;
//import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    private  Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Service层切点
     */
    @Pointcut("@annotation(com.example.jwt.domain.annotations.ServiceLogs)")
    public void serviceAspect() {

    }

    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.example.jwt.domain.annotations.LoginLogs)")
    public void controllerAspect() {

    }
    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //类名
            String className = joinPoint.getTarget().getClass().getName();
            //请求方法
            String method =  joinPoint.getSignature().getName() + "()";
            //方法参数
            String methodParam = JSON.toJSONString(joinPoint.getArgs());
            Map<String, String[]> params = request.getParameterMap();

            // 记录下请求内容
            logger.info("URL : " + request.getRequestURL().toString());
            logger.info("HTTP_METHOD : " + request.getMethod());
            logger.info("IP : " + request.getRemoteAddr());
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

            String decode = "";
            //针对get请求
            if(request.getQueryString()!=null){
                try {
                    decode = URLDecoder.decode(request.getQueryString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                //针对post请求
                for (String key : params.keySet()) {
                    String[] values = params.get(key);
                    for (int i = 0; i < values.length; i++) {
                        String value = values[i];
                        decode += key + "=" + value + "&";
                    }
                }
            }
            //将String根据&转成Map
            Map<String, Object> methodParamMap = transStringToMap(decode, "&", "=");
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //方法描述
            String methodDescription = getControllerMethodDescription(joinPoint);
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n");
            sb.append("*********************************Request请求***************************************");
            sb.append("\n");
            sb.append("ClassName     :  ").append(className).append("\n");
            sb.append("RequestMethod :  ").append(method).append("\n");
            sb.append("RequestParams :  ").append(methodParam).append("\n");
            sb.append("RequestType   :  ").append(request.getMethod()).append("\n");
            sb.append("Description   :  ").append(methodDescription).append("\n");
            sb.append("serverAddr    :  ").append(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()).append("\n");
            sb.append("RemoteAddr    :  ").append(IpUtils.getRemoteAddr(request)).append("\n");
            UserAgent userAgent = UserAgentUtils.getUserAgent(request);
            sb.append("DeviceName    :  ").append(userAgent.getOperatingSystem().getName()).append("\n");
            sb.append("BrowserName   :  ").append(userAgent.getBrowser().getName()).append("\n");
            sb.append("UserAgent     :  ").append(request.getHeader("User-Agent")).append("\n");
            sb.append("RequestUri    :  ").append(StringUtils.abbr(request.getRequestURI(), 255)).append("\n");
            logger.info(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private LoginLogs getAnnotationInfo(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Method method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(),
                ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes());
        LoginLogs loginLogs = method.getAnnotation(LoginLogs.class);
        return loginLogs;
    }

    @AfterReturning(returning = "ret", pointcut = "DepartmentRestController()")
    public void doAfterReturning(Object ret) throws Throwable {
        /*处理完请求，返回内容*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求方法
        String method = StringUtils.abbr(request.getRequestURI(), 255);
        StringBuilder sb = new StringBuilder(1000);
        // 处理完请求，返回内容
        sb.append("\n");
        sb.append("Result        :  ").append(ret);
        logger.info(sb.toString());
        logger.info("RESPONSE : " + ret);
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //类名
            String className = joinPoint.getTarget().getClass().getName();
            //请求方法
            String method =  (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            //方法参数
            String methodParam = Arrays.toString(joinPoint.getArgs());
            //方法描述
            String methodDescription = getServiceMethodDescription(joinPoint);
            //获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
                }
            }
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n");
            sb.append("*********************************Service异常***************************************");
            sb.append("\n");
            sb.append("ClassName        :  ").append(className).append("\n");
            sb.append("Method           :  ").append(method).append("\n");
            sb.append("Params           :  ").append("[" + params + "]").append("\n");
            sb.append("Description      :  ").append(methodDescription).append("\n");
            sb.append("ExceptionName    :  ").append(ex.getClass().getName()).append("\n");
            sb.append("ExceptionMessage :  ").append(ex.getMessage()).append("\n");
            logger.info(sb.toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceLogs.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */

    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        /*这是个工具类， 通过joinPoint对象 获得目标对象的引用 获得拦截对象的Class对象，然后获得Class对象的名称*/
        String targetName = joinPoint.getTarget().getClass().getName();
        /*获得拦截方法的名称*/
        String methodName = joinPoint.getSignature().getName();
        /*获得拦截方法的参数对象数组*/
        Object[] arguments = joinPoint.getArgs();
       /* 获得拦截对象的Class*/
        Class<?> targetClass = Class.forName(targetName);
        /*获得拦截对象的所有方法*/
        Method[] methods = targetClass.getMethods();
        String description = "";
      /*  遍历方法*/
        for (Method method : methods) {
            /*当此方法的名称与拦截方法的名称相同时候*/
            if (method.getName().equals(methodName)) {
                /*获得这个方法的所有参数类型的 Class对象*/
                Class[] clazzs = method.getParameterTypes();
                /*确保拦截的方法的参数个数 与 解析到的参数个数相同*/
                if (clazzs.length == arguments.length) {
                    /*然后获得方法的注解的description字段的值 并且返回*/
                    description = method.getAnnotation(LoginLogs.class).value();
                    break;
                }
            }
        }
        return description;
    }

    /**string和map的转换
     *
     * @param mapString
     * @param separator
     * @param pairSeparator
     * @return
     */
    public static Map<String, Object> transStringToMap(String mapString, String separator, String pairSeparator) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] fSplit = mapString.split(separator);
        for (int i = 0; i < fSplit.length; i++) {
            if (fSplit[i]==null||fSplit[i].length()==0) {
                continue;
            }
            String[] sSplit = fSplit[i].split(pairSeparator);
            String value = fSplit[i].substring(fSplit[i].indexOf('=') + 1, fSplit[i].length());
            map.put(sSplit[0], value);
        }
        return map;
    }




}





