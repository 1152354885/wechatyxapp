package com.me.test.service;

import com.me.test.pojo.ActiVity;
import com.me.test.pojo.SignIn;
import com.me.test.pojo.UserInfo;
import com.me.test.pojo.WxUser;



public interface SignInService {
    //
    void add(SignIn signIn);

    SignIn querySignInById( String openid);

    void updateSignIn(SignIn signIn);

    public SignIn queryByOpenId(String openid);

}
