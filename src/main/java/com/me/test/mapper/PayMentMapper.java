package com.me.test.mapper;

import com.me.test.pojo.PayMent;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface PayMentMapper {


    void add(PayMent payMent);

    /**
     * 删除一条数据
     * @param id 被删除数据的id
     */
    void delete(Integer id);

    /**
     * 修改一条数据
     * @param payMent v修改的数据
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

    PayMent selectUser(PayMent payMent);


}
