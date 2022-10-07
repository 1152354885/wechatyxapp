package com.me.test.mapper;


import com.me.test.pojo.WxUser;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WxUserMapper {
    /**
     * 添加用户
     * @param
     * @return
     */
    int addWxUser(WxUser wxuser);
    /**
     * 通过openid查找用户
     * @param openid
     * @return
     */
    WxUser queryWxUserById(@Param("openid") String openid);

    WxUser queryWxUserByIdTwo(@Param("apply_openid") String apply_openid);

    /**
     * 更新用户信息
     * @param
     * @return
     */
    int updateWxUser(WxUser wxuser);


  WxUser queryWxUserAndActivityByOpenID(@Param("openid") String openid);
    /**
     * 更新用户信息
     * @param
     * @return
     */
    void EditWxUser(WxUser wxUser);
}
