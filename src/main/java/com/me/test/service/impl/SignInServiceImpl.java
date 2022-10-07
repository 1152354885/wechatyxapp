package com.me.test.service.impl;

import com.me.test.mapper.SignInMapper;
import com.me.test.pojo.SignIn;

import com.me.test.service.SignInService;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
   private    SignInMapper signInMapper;

    @Override
    public void add(SignIn signIn) {
       signInMapper.add(signIn);

    }

    @Override
    public SignIn querySignInById(String openid) {
        SignIn signIn = signInMapper.querySignInById(openid);
        return  signIn;
    }

    @Override
    public void updateSignIn(SignIn signIn) {
        signInMapper.updateSignIn(signIn);
    }

    @Override
    public SignIn queryByOpenId(String openid) {
        return signInMapper.querySignInById(openid);
    }


}
