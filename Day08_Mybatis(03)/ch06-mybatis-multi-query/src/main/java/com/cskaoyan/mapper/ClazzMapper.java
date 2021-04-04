package com.cskaoyan.mapper;

import com.cskaoyan.dto.Clazz;
import org.apache.ibatis.annotations.Param;

public interface ClazzMapper {

    Clazz selectClazzWithStudentsByName(@Param("name") String  name);
}
