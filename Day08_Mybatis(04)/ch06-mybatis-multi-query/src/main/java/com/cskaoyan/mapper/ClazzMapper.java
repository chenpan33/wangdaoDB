package com.cskaoyan.mapper;

import com.cskaoyan.dto.Clazz;
import org.apache.ibatis.annotations.Param;

public interface ClazzMapper {

    // 连接查询
    Clazz selectClazzWithStudentsByName(@Param("name") String  name);

    // 分次查询
    Clazz selectClazzWithStudentsByName2(@Param("name") String  name);
}
