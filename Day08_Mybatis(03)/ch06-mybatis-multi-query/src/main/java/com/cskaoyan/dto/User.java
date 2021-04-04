package com.cskaoyan.dto;


import lombok.*;
import org.omg.PortableInterceptor.INACTIVE;


// 这些方法是编译的时候生成的
@Data
public class User {

    Integer id;
    String username;
    String password;
    Integer age;

    UserDetail userDetail;

}
