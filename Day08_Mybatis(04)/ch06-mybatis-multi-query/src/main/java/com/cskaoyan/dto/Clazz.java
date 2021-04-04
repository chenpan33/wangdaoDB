package com.cskaoyan.dto;

import lombok.Data;

import java.util.List;

@Data
public class Clazz {

    private Integer id;

    private String name;

    private List<Student> studentList;
}
