package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        printFromDatabase(org.example1._1.BlogMapper.class, sqlSessionFactory);
        printFromDatabase(org.example._1.BlogMapper.class, sqlSessionFactory);
    }

    private static void printFromDatabase(Class<?> clazz, SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Object mapper = session.getMapper(clazz);
            System.out.println(clazz.getMethod("selectBlog", String.class, String.class)
                    .invoke(mapper, "1", "2"));
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}