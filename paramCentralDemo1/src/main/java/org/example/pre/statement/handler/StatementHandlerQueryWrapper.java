package org.example.pre.statement.handler;

import org.apache.ibatis.plugin.Invocation;

public class StatementHandlerQueryWrapper {

    public static final String methodName = "query";

    public static Object query(Invocation invocation) {
        throw new NoSuchMethodError();
    }
}
