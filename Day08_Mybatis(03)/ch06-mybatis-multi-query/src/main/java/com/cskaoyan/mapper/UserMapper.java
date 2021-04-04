package com.cskaoyan.mapper;

import com.cskaoyan.dto.User;
import com.cskaoyan.dto.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectUserWithDetailById(@Param("id") Integer id);

}
