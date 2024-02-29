package org.example;


import org.example.tmp0.mapper.Demo1Mapper;
import org.example.tmp0.mapper.DemoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.tmp0.mapper")
public class Application implements CommandLineRunner {


    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private Demo1Mapper demo1Mapper;

    @Override
    public void run(String...args) throws Exception {
        System.out.println(demoMapper.getCount());

        for (int i = 0; i < 10; i++) {
            new Thread(()-> System.out.println(demo1Mapper.getCount())).start();
        }

        System.out.println(demo1Mapper.getCount());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
