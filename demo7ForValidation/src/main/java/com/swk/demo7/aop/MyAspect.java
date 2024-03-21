package com.swk.demo7.aop;

import com.swk.demo7.handlers.MyHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @Around(value = "execution(* com.swk.demo7.services.DTOValidateService.check(..))")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            MyHandler.assertCheckpoint(joinPoint.getThis(),
                    ((MethodSignature) joinPoint.getSignature()).getMethod(),
                    joinPoint.getArgs());
        }catch (Exception e){
            e.printStackTrace();
        }
        return joinPoint.proceed();
    }

}
