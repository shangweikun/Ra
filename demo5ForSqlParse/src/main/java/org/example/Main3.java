package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main3 {

    @Data
    @AllArgsConstructor
    private static class DTO {
        private String id;
        private String time;
        private String text;
    }

    public static void main(String[] args) {
        List<DTO> list = new ArrayList<>();
        DTO dto = new DTO("0", "20231231", "dto");
        DTO dto1 = new DTO("1", "20240101", "dto1");
        DTO dto2 = new DTO("2", "20240102", "dto2");
        DTO dto3 = new DTO("3", "20240103", "dto3");
        DTO dto4 = new DTO("4", "20240104", "dto4");
        list.add(dto);
        list.add(dto1);
        list.add(dto2);
        list.add(dto3);
        list.add(dto4);

        list.stream()
                .filter(i -> "20240102".compareTo(i.time) >= 0)
                .filter(i -> "20231231".compareTo(i.time) <0 )
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
