package org.example.threadlocals;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalContext {

    private static final ThreadLocal<ThreadLocalContext> THREAD_LOCAL_CONTEXT
            = ThreadLocal.withInitial(ThreadLocalContext::new);

    public static ThreadLocalContext context() {
        return THREAD_LOCAL_CONTEXT.get();
    }

    public static void init() {
        THREAD_LOCAL_CONTEXT.remove();
    }

    private final Map<String, Object> context = new HashMap<>();

    public Map<String, Object> getContext() {
        return context;
    }
}
