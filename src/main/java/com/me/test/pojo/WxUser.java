package com.me.test.pojo;



import lombok.*;

import java.util.List;

/**
 * @author BeiChen
 * @version 1.0
 * @date 2021/4/8 19:20
 */

@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WxUser {
    /**
     * openid
     * skey
     * 市
     * 省
     * 国
     * 头像
     * 性别
     * 昵称
     *最后登录时间
     *创建时间
     */

    private String open_id;//微信ID
    private String skey;//会话ID
    private String session_key;
    private String city;
    private String province;
    private String country;
    private String avatar_url;
    private String gender;
    private String nick_name;
    private String last_visit_time;
    private String create_time;
    private Integer age;
    private String motto;
    private String phone;
    private List<ActiVity> actiVities;

}

