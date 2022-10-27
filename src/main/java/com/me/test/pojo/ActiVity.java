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
public class ActiVity {
    private Integer id;//活动id 自增
    private String act_type;
    private String title;
    private String content;//正文
    private String publish_time;
    private String act_addr;
    private String act_time;
    private String nick_name;
    private String avatar_url;
    private String link;
    private String open_id;//微信ID
    private String detail_addr;
    private String count;
    private String image_url;
    private Integer  collect_count;
    private List<Reply> replies;
    private List<Apply> applies;
    private List<Collect> collects;
}

