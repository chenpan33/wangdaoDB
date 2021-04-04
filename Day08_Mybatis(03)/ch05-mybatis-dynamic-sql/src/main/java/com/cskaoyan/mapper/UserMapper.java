package com.cskaoyan.mapper;

import com.cskaoyan.dto.User;
import com.cskaoyan.dto.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 根据用户名、id去查询用户
    User selectUserByIdAndUserName(@Param("id") Integer id,
                                   @Param("username") String username);


    // 现在有一个需求，我们传入一个数值，假如这个数值比20大，那么我们就查询出所有的年龄比20大的用户
    // 假如我们传入的数值比20小，那么我们就查询出所有年龄比20小的用户
    List<User> selectUserLisyByAge(@Param("age") Integer age);

    // if语句的第二个使用场景
    // 根据我们传入的条件去匹配用户
    List<User> selectUserBySelf(@Param("user") User user);


    // 现在有一个需求，我们传入一个数值，假如这个数值比20大，那么我们就查询出所有的年龄比20大的用户
    // 假如我们传入的数值比20小，那么我们就查询出所有年龄比20小的用户
    List<User> selectUserLisyByAge2(@Param("age") Integer age);

    //根据我们传入的条件去修改用户
    // 假如我们现在想要修改传入的条件，也就是假如我们传入了username，那么就去修改username，如果没有传入，就不修改
    Integer updateUserBySelf(@Param("user") User user);


    // 通过set标签来实现用户的动态修改
    Integer updateUserByIdWithSet(@Param("user") User user);

    User selectUserById(@Param("id") Integer id);


    // 插入一个list
    Integer insertUserList(@Param("userList") List<User> userList);

    Integer insertUserList2(List<User> userList);

    // 批量插入数组
    Integer insertUserList3(User[] userarr);

    Integer insertUserList4(@Param("userarr") User[] userarr);

    // 通过一个idList来查找userList
    List<User> selectUserListByIdList(@Param("idList") List<Integer> idList);


    // selectKey 标签
    Integer insertUser(@Param("user") User user);

    // userGeneratedKeys 标签
    Integer insertUser2(@Param("user") User user);
}
