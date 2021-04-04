package com.cskaoyan.mapper;

import com.cskaoyan.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectUserById(@Param("id") Integer id);

    User selectUserByIdAndUsername(@Param("id") Integer id,
                                   @Param("username") String username);


    // 插入用户
    Integer insertUser(User user);

    // 插入用户
    Integer insertUser2(@Param("user") User user);

    // 通过Map来传值
    List<User> selectUserByMap(@Param("map") Map<String, Object> map);


    // 通过位置来传值
    User selectUserByIdAndUsername2(Integer id, String username);

    User selectUserById$(@Param("id") Integer id);


    List<User> selectUserListByTableName(@Param("tableName") String tableName);


    User selectUserOrderBy(@Param("columnName") String columnName);


}
