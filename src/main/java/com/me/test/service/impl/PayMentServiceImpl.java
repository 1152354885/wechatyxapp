package com.me.test.service.impl;
import com.me.test.mapper.PayMentMapper;

import com.me.test.pojo.PayMent;


import com.me.test.service.PayMentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayMentServiceImpl implements PayMentService {


    @Autowired
    private PayMentMapper payMentMapper;

    @Override
    public void add(PayMent payMent) {
        payMentMapper.add(payMent);
    }

    @Override
    public void delete(Integer id) {
        payMentMapper.delete(id);
    }

    @Override
    public void update(PayMent payMent) {
        payMentMapper.update(payMent);
    }

    @Override
    public PayMent queryById(Integer id) {
        return payMentMapper.queryById(id);
    }

    @Override
    public List<PayMent> queryAll() {
        return payMentMapper.queryAll();
    }
    @Override
    public boolean login(String serial, String result) {
        //   UserEntity userEntity = new UserEntity ();
       // UserInfo userInfo=new UserInfo();
        PayMent payMent=new PayMent();
//        userEntity.setUsername ( username );
//        userEntity.setPassword ( password );
        payMent.setSerial( serial );
    //    userInfo.setPassword( password );
     payMent.setResult(result);

        PayMent user = payMentMapper.selectUser(payMent);
        if (user != null){
            return true;
        }
        return false;

    }
}
