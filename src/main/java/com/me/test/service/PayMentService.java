package com.me.test.service;

import com.me.test.pojo.PayMent;


import java.util.List;

public interface PayMentService {
    /**
     * 增加一条数据
     * @param payMent 数据
     */
    void add(PayMent payMent);

    /**
     * 删除一条数据
     * @param id 被删除数据的id
     */
    void delete(Integer id);

    /**
     * 修改一条数据
     * @param payMent 修改的数据
     */
    void update(PayMent payMent);

    /**
     * 根据id去查询一条数据
     * @param id 查询的id
     */
    PayMent queryById(Integer id);

    /**
     * 查询全部数据
     * @return
     */
    List<PayMent> queryAll();
    boolean login(String serial,String result);
}
