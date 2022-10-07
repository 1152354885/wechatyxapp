package com.me.test.service.impl;

import com.me.test.mapper.WxUserMapper;
import com.me.test.pojo.WxUser;
import com.me.test.service.WxUserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackForClassName = "Exception.class")
public class WxUserServiceImpl implements WxUserService {

    private final WxUserMapper wxUserMapper;

    public WxUserServiceImpl(WxUserMapper wxUserMapper) {
        this.wxUserMapper = wxUserMapper;
    }

    @Override
    public WxUser queryWxUserById(String openid) {
        WxUser wxuser = wxUserMapper.queryWxUserById(openid);
        return wxuser;
    }
    /**
     * 添加新用户
     * @param
     * @return
     */
    @Override
    public int addWxUser(WxUser wxuser) {
        return wxUserMapper.addWxUser(wxuser);
    }

    /**
     * 更新用户信息
     * @param
     * @return
     */
    @Override
    public int updateWxUser(WxUser wxuser) {
        return wxUserMapper.updateWxUser(wxuser);
    }

    @Override
    public WxUser queryWxUserAndActivityByOpenID(String openid) {
        return wxUserMapper.queryWxUserAndActivityByOpenID(openid);
    }

    @Override
    public void EditWxUser(WxUser wxUser) {
         wxUserMapper.EditWxUser(wxUser);
    }

}

