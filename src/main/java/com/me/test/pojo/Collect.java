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
public class Collect {
     private Integer collect_id;
     private Integer act_id;
     private String  openid;
     private String  avatar;
     private String  nickname;
     private String  collectcore;
     private Boolean status;
     private String collect_time;
     private List<ActiVity> actiVities;
}
