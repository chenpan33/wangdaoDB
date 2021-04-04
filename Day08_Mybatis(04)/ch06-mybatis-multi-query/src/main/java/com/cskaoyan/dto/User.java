package com.cskaoyan.dto;


import lombok.*;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;


// 这些方法是编译的时候生成的
@Data
public class User implements Serializable {

    // 这个序列化id是有什么作用呢？其实就是在进行反序列化的时候，去对比这个id，防止我们序列化之后的内容被篡改

    private static final long serialVersionUID = -2266241902551610556L;
    Integer id;
    String username;
    String password;
    Integer age;

    UserDetail userDetail;

}
