package com.cskaoyan.dto.multi2multi;

import lombok.Data;

import java.util.List;

@Data
public class Course {

    Integer id;
    String name;

    List<Student> studentList;
}
