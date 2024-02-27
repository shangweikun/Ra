
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

public class ProxyMethodParamMain {

    interface MyInterface {
        void performAction(String test);
    }

    static class MyInterfaceImpl implements MyInterface {
        public void performAction(String test) {
            System.out.println("Action performed " + test);
        }
    }

    static class MyInvocationHandler implements InvocationHandler {

        private final Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before method " + method.getName());
            Object result = method.invoke(target, args);
            System.out.println("After method " + method.getName());
            return result;
        }
    }


    public static void main(String[] args) throws NoSuchMethodException {
        MyInterface original = new MyInterfaceImpl();
        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class<?>[]{MyInterface.class},
                new MyInvocationHandler(original)
        );

        Method performAction = proxyInstance.getClass().getMethod("performAction", String.class);
        Parameter[] parameters = performAction.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }
    }
}
