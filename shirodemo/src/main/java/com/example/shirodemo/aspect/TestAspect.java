package com.example.shirodemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class TestAspect {

    //拦截所有controller下的请求
    @Pointcut("execution(* com.example.shirodemo.controller.*.*(..))")
    public void TestAspect(){

    }

    @Around("TestAspect()")
    public Object aroundVisit(ProceedingJoinPoint pjp) throws Exception, Throwable{
        //获取请求信息
        Signature signature = pjp.getSignature();
        System.out.println(signature.getName());
        System.out.println(signature.getDeclaringTypeName());
        //获取请求头
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 如果有session则返回session如果没有则返回null(避免创建过多的session浪费内存)
        HttpSession session = request.getSession(false);
        // 获取请求头信息模板
        System.out.println(request.getHeader("message"));
        //获取请求的返回值
        Object o = pjp.proceed();
        System.out.println(o.toString());
        return o;
    }
}
