package com.me.test.pojo;

import lombok.*;

@Data
@Getter
@Setter
//使用@AllArgsConstructor自动生成有参构造
@AllArgsConstructor
//使用@NoArgsConstructor自动生成无参构造
@NoArgsConstructor

public class SecondReply {
    private Integer sec_reply_id;
    private String  sec_comments;
    private String  sec_reply_openid;
    private String  sec_reply_time;
    private String  sec_name;
    private String  master_replyid;
    private String  master_replyopenid;
    private String  sec_avatar;
    private String  actid;
    private Reply   reply;
}
