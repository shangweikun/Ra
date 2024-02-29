package com.f.s.directory;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapperDirectory {
    private MapperDirectory() {
    }

    private static final Map<String, MapperMultipleDatabaseProperty>
            _beanName2properties = new HashMap<>();
    private static final Map<Class<?>, MapperMultipleDatabaseProperty>
            _class2properties = new HashMap<>();

    public static Optional<MapperMultipleDatabaseProperty> getByBeanName(String beanName) {
        return Optional.ofNullable(_beanName2properties.get(beanName));
    }

    public static Optional<MapperMultipleDatabaseProperty> getByClass(Class<?> clazz) {
        return Optional.ofNullable(_class2properties.get(clazz));
    }

}
