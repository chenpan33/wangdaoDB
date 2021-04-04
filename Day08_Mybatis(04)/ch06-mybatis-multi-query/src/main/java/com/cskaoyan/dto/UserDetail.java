package com.cskaoyan.dto;

import lombok.Data;

@Data
public class UserDetail {


    // 说明一下，这个地方建议大家以后都写包装类型，因为写成包装类型和很多框架更加适配，你遇到的问题会更少
    private Integer id;

    private Integer userId;

    private String pic;

    private String sign;
}

