package org.example1._1;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicProxyDemo {

    public static void main(String[] args) throws Exception {
        // Create an instance of the interface implementation
        Greeting greetingImpl = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hello, " + name);
            }
        };

        // Create a dynamic proxy for the interface
        Greeting greetingProxy = (Greeting) Proxy.newProxyInstance(
                Greeting.class.getClassLoader(),
                new Class[]{Greeting.class},
                new GreetingInvocationHandler(greetingImpl)
        );

        // Invoke a method on the proxy instance
        greetingProxy.sayHello("World");

        // Generate the source code for the dynamic proxy class
        String proxyClassName = Proxy.getProxyClass(Greeting.class.getClassLoader(), Greeting.class).getName();
        String proxySourceCode = generateProxySourceCode(proxyClassName, Greeting.class);
        System.out.println("Generated source code for the proxy class:");
        System.out.println(proxySourceCode);

        // Save the generated source code to a file
        String fileName = proxyClassName.substring(proxyClassName.lastIndexOf('.') + 1) + ".java";
        Files.write(Paths.get(fileName), proxySourceCode.getBytes());
        System.out.println("Proxy source code written to " + fileName);
    }

    private static String generateProxySourceCode(String proxyClassName, Class<?> interfaceClass) {
        StringBuilder sourceCode = new StringBuilder();
        String packageName = proxyClassName.substring(0, proxyClassName.lastIndexOf('.'));
        String simpleClassName = proxyClassName.substring(proxyClassName.lastIndexOf('.') + 1);
        String interfaceName = interfaceClass.getName();

        sourceCode.append("package ").append(packageName).append(";\n\n");
        sourceCode.append("public class ").append(simpleClassName)
                .append(" implements ").append(interfaceName).append(" {\n\n");
        sourceCode.append("    private InvocationHandler invocationHandler;\n\n");
        sourceCode.append("    public ").append(simpleClassName)
                .append("(InvocationHandler invocationHandler) {\n");
        sourceCode.append("        this.invocationHandler = invocationHandler;\n");
        sourceCode.append("    }\n\n");

        // Generate methods
        for (Method method : interfaceClass.getMethods()) {
            sourceCode.append("    @Override\n");
            sourceCode.append("    public ").append(method.getReturnType().getName()).append(" ")
                    .append(method.getName()).append("(");
            // Method parameters
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                sourceCode.append(params[i].getName()).append(" param").append(i);
                if (i < params.length - 1) {
                    sourceCode.append(", ");
                }
            }
            sourceCode.append(") {\n");
            sourceCode.append("        // Method body can be generated here\n");
            sourceCode.append("    }\n\n");
        }

        sourceCode.append("}\n");
        return sourceCode.toString();
    }
}
