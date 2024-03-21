package com.swk.demo7.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class DTO1<T> implements Serializable {

    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private T data;
}
