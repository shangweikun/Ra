package com.example.demo;

import java.sql.SQLException;


/**
 * https://my.oschina.net/u/4526289/blog/5438231
 * @param <T>
 */
public class ExceptionForQuiz<T extends Exception> {

    @SuppressWarnings("unchecked")
    private void pleaseThrow(final Exception t) throws T {
        //由于泛型擦除，没有出现ClassCastException问题
        throw (T) t;
    }

    public static void main(final String[] args) {

        try {
            new ExceptionForQuiz<RuntimeException>().pleaseThrow(new SQLException());
        } catch (final Exception ex) {
            System.out.println("Jerry print");
            System.out.println(ex.getClass());
        }
        System.out.println("Marry print");
    }

}