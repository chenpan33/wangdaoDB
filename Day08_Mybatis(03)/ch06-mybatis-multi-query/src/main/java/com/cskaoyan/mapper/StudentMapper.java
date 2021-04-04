package com.cskaoyan.mapper;

import com.cskaoyan.dto.multi2multi.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

    // 根据学生姓名去查询学生以及他的选课信息
    Student selectStudentWithCoursesByName(@Param("name") String name);
}
