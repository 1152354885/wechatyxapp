package com.me.test.service;

import com.me.test.pojo.ActiVity;
import com.me.test.pojo.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {
    public void add (Reply reply);

    void delete(Integer reply_id);

    public List<Reply> queryByOpenId(String reply_openid);

    void   update(Reply reply);

    List<Reply> getMyReplyByMasterOpenid(@Param("master_openid")String master_openid);

    Integer ReplyNotice(@Param("openid")String openid);
}
