package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main7 {

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

        System.out.println(list.stream()
                .min(Comparator.comparing(DTO::getTime)).orElse(null));

    }
}
