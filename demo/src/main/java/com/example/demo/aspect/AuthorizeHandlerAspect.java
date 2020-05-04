package com.example.demo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class AuthorizeHandlerAspect {

	final static Logger logger = LoggerFactory.getLogger(AuthorizeHandlerAspect.class);

	@Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
	public void handlerMethod() {}

    @Before("handlerMethod()")
    public void interceptHandlerMethod(JoinPoint jp) {
    	System.out.println("xxxx");

    }


}
