package com.example._15.demo.handlers.impl;

import com.example._15.demo.handlers.Info;
import com.example._15.demo.handlers.InfoHandle;

import java.util.List;

public class StartInfo implements InfoHandle {
    @Override
    public String getColumnName() {
        return "start";
    }

    @Override
    public void handle(Info info, List<String> input) {
    }
}
