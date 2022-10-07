package com.me.test.pojo;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
//使用@AllArgsConstructor自动生成有参构造
@AllArgsConstructor
//使用@NoArgsConstructor自动生成无参构造
@NoArgsConstructor
public class Reply {
    private Integer reply_id;
    private String comments;
    private String reply_openid;
    private String master_openid;
    private String name;
    private String avatar;
    private String reply_time;
    private Integer act_id;
   // private List<Reply> replies;
    private List<SecondReply> secondReplies;

}
