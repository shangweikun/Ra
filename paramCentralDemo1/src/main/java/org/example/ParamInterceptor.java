package org.example;

import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.example.pre.executor.base.BaseExecutorUpdateWrapper;
import org.example.threadlocals.ThreadLocalContext;

import java.lang.reflect.Method;
import java.util.Map;

import static org.example.constant.SdkConstant.PARAM_TABLE_UPDATE_MARK;

public class ParamInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Map<String, Object> context = ThreadLocalContext.context().getContext();
        if (!(boolean) context.get(PARAM_TABLE_UPDATE_MARK)) {
            return invocation.proceed();
        }

        Method method = invocation.getMethod();
        Object target = invocation.getTarget();

        if (target instanceof BaseExecutor
                && BaseExecutorUpdateWrapper.methodName.equals(method.getName())) {
            return BaseExecutorUpdateWrapper.update(invocation);
        }else {
            return invocation.proceed();
        }
    }
}
