package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.constant.SdkConstant;
import org.example.threadlocals.ThreadLocalContext;

import java.util.Map;

@Aspect
public class ParamTableDaoAspect {

    @Pointcut("@annotation(ParamDao)")
    public void publicMethod() {
    }

    @Around("publicMethod()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ThreadLocalContext.init();
        Map<String, Object> context = ThreadLocalContext.context().getContext();
        context.put(SdkConstant.PARAM_TABLE_UPDATE_MARK, true);
        return proceedingJoinPoint.proceed();
    }
}
