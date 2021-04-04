package com.cskaoyan.mapper;

import com.cskaoyan.dto.User;
import com.cskaoyan.dto.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 输出映射 简单类型（Java 中的基本类型和String）
    Integer selectUserAgeById(Integer id);


    // 查表有多少行
    Integer selectCount();


    User selectUserById(@Param("id") Integer id);

    UserVO selectUserById2(@Param("id") Integer id);

    // 简单类型的List
    List<String> selectUserNameList();


    List<User> selectUserList();


    // resultMap
    UserVO selectUserById3(@Param("id") Integer id);


    // resultMap
    List<UserVO> selectUserVOList();


    UserVO selectVO(Integer id);
}
