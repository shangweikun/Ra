package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListTypeTest {

    @SuppressWarnings({"unchecked","rawtypes"})
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("2");
        list = (List<Object>) list.stream()
                .map(i->"1")
                .collect(Collectors.toList());
    }
}
