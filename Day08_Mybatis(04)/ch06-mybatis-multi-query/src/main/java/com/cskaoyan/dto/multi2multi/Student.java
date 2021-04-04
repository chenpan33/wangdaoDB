package com.cskaoyan.dto.multi2multi;


import lombok.Data;

import java.util.List;

@Data
public class Student {

    Integer id;
    String name;
    Integer age;

    List<Course> courseList;
}
