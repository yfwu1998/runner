package com.wuyifeng.runner.api.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一处理Web请求日志
 */
@Component
@Aspect
public class HttpAspect {

    private Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    //    修饰符匹配（modifier-pattern?）
//    返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
//    类路径匹配（declaring-type-pattern?）
//    方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
//    参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
//    异常类型匹配（throws-pattern?）
//    其中后面跟着“?”的是可选项
    @Pointcut("execution(public * com.wuyifeng.runner.api.controller.*.*(..))")
    public void cut(){}

    @Before("cut()")
    public void doBefore(JoinPoint joinPoint){
        //记录请求的url

        //RequestContextHolder，在非web控制器层获取request、response等对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url = " + request.getRequestURI());
        //method
        logger.info("method = "+ request.getMethod());
        //ip
        logger.info("ip = "+request.getRemoteAddr());


    }


    //在调用上面 @Pointcut标注的方法后执行。用于获取返回值
    @AfterReturning(returning = "obj", pointcut = "cut()")
    public void doAfterReturning(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("response="+ objectMapper.writeValueAsString(obj));
    }


}
