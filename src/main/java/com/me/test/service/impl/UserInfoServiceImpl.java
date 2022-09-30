package com.me.test.service.impl;


import com.me.test.mapper.UserInfoMapper;


import com.me.test.pojo.UserInfo;


import com.me.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {



    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int add(UserInfo userInfo) {
        userInfoMapper.add(userInfo);
        return 0;
    }

    @Override
    public void delete(Integer id) {
        userInfoMapper.delete(id);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.update(userInfo);

    }

    @Override
    public UserInfo queryById(Integer id) {
        return userInfoMapper.queryById(id);
    }

    @Override
    public List<UserInfo> queryAll() {
        return userInfoMapper.queryAll();
    }

    @Override
    public boolean login(String username, String password) {
     //   UserEntity userEntity = new UserEntity ();
        UserInfo userInfo=new UserInfo();
//        userEntity.setUsername ( username );
//        userEntity.setPassword ( password );
        userInfo.setUsername( username );
        userInfo.setPassword( password );


        UserInfo user = userInfoMapper.selectUser( userInfo);
        if (user != null){
            return true;
        }
        return false;

    }

}
