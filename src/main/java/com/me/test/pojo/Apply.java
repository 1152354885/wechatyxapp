package com.me.test.pojo;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
//使用@AllArgsConstructor自动生成有参构造
@AllArgsConstructor
//使用@NoArgsConstructor自动生成无参构造
@NoArgsConstructor
public class Apply {
    private Integer apply_id;
    private String master_openid;
    private String apply_openid;
    private String application_time;
    private String agree_time;
    private String apply_core;
    private Integer act_id;
    private String apply_avatar;
    private String apply_nickname;
    private String states;
    private String message;
    private ActiVity actiVity;
    private WxUser wxUser;
    private Integer chek;
    private String apply_link;
}
