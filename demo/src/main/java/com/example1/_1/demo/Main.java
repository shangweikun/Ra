package com.example1._1.demo;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // 创建VelocityEngine实例对象
        VelocityEngine velocityEngine = new VelocityEngine();

        // 设置Velocity资源加载器
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        // 初始化Velocity引擎，传入配置Properties
        velocityEngine.init(p);

        // 获取模板文件
        Template template = velocityEngine.getTemplate("hello.vm");

        // 创建上下文实例
        VelocityContext context = new VelocityContext();

        // 向上下文中填入数据
        context.put("name", "World");

        // 渲染模板
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        // 输出结果
        System.out.println(writer);
    }
}
