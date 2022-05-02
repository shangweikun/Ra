package com.example._3.demo;

public enum TestEnum {

    ENUM1,
    ENUM2;

    public enum SubEnum {
        SUBE_NUM(ENUM1);

        final TestEnum testEnum;

        SubEnum(TestEnum testEnum){
            this.testEnum = testEnum;
        }
    }
    
}
