package com.f.s.directory;

import java.util.List;

public class MapperMultipleDatabaseProperty {

    private String type = "1";

    private Class<?> clazz;

    private List<String> methodFullNames;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<String> getMethodFullNames() {
        return methodFullNames;
    }

    public void setMethodFullNames(List<String> methodFullNames) {
        this.methodFullNames = methodFullNames;
    }
}
