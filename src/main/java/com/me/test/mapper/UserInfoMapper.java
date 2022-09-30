package com.me.test.mapper;

import com.me.test.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface UserInfoMapper {

    void add(UserInfo userInfo);

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

    UserInfo selectUser(UserInfo userInfo);


}
