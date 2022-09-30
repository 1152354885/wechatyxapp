package com.me.test.service;

import com.me.test.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {

    int add(UserInfo userInfo);

    /**
     * 删除一条数据
     * @param id 被删除数据的id
     */
    void delete(Integer id);


    void update(UserInfo userInfo);

    /**
     * 根据id去查询一条数据
     * @param id 查询的id
     */
    UserInfo queryById(Integer id);

    /**
     * 查询全部数据
     * @return
     */
    List<UserInfo> queryAll();


    boolean login(String username,String password);
}
