package com.me.test.mapper;

import com.me.test.pojo.SignIn;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SignInMapper {

    void add(SignIn signIn);
    SignIn querySignInById(@Param("openid") String openid);
    void updateSignIn(SignIn signIn);

    //List<SignIn> queryAll();

    public SignIn queryByOpenId(String openid);
}
