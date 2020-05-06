package com.example.demo.aspect;


import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.Authorize;



@Aspect
@Component
public class AuthorizeHandlerAspect {

	final static Logger logger = LoggerFactory.getLogger(AuthorizeHandlerAspect.class);

	@Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
	public void handlerMethod() {}

    @Before("handlerMethod()")
    public void interceptHandlerMethod(JoinPoint jp) {


    	MethodSignature methodSignature = (MethodSignature) jp.getSignature();
    	Method method = methodSignature.getMethod();

        // メソッドに付与されたアノテーション@Authorizeを取得する。
        Authorize methodAnno = method.getAnnotation(Authorize.class);

        if(methodAnno != null) {
        	//System.out.println(methodAnno.roles());
        }
    }
}
