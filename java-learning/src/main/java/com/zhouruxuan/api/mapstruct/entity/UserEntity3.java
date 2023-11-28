package com.zhouruxuan.api.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity3 {
    private Long id;
    private Date gmtCreate;
    private Date createTime;
    private Long buyerId;
    private String age;
    private String userNick1;
    private String userVerified;
    private Attributes attributes;
}
