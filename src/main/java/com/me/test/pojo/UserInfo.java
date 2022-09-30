package com.me.test.pojo;


import lombok.*;

//使用@Data自动生成需要的get、set
@Data
//使用@AllArgsConstructor自动生成有参构造
@AllArgsConstructor
//使用@NoArgsConstructor自动生成无参构造
@NoArgsConstructor
@Setter
@Getter
public class UserInfo {


    private Integer    id;
    private String password;
    private String username;
   // private Boolean authority=false;
}
