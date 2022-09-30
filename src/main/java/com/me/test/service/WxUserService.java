package com.me.test.service;

import com.me.test.pojo.WxUser;


public interface WxUserService {

    WxUser queryWxUserById(String openid);

    int addWxUser(WxUser wxuser);

    int updateWxUser(WxUser wxuser);

    WxUser queryWxUserAndActivityByOpenID( String openid);

    void EditWxUser(WxUser wxUser);
}
