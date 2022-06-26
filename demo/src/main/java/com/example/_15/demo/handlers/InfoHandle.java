package com.example._15.demo.handlers;

import java.util.List;

public interface InfoHandle {

    String getColumnName();

    void handle(Info info, List<String> input);

}
