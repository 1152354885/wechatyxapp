package com.me.test.service.impl;

import com.me.test.mapper.ReplyMapper;
import com.me.test.pojo.ActiVity;
import com.me.test.pojo.Reply;
import com.me.test.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Override
    public void add(Reply reply) {
        replyMapper.add(reply);
    }

    @Override
    public void delete(Integer reply_id) {
        replyMapper.delete(reply_id);
    }

    @Override
    public List<Reply> queryByOpenId(String reply_openid) {
        return replyMapper.queryByOpenId(reply_openid);
    }


    @Override
    public void update(Reply reply) {
        replyMapper.update(reply);
    }

    @Override
    public List<Reply> getMyReplyByMasterOpenid(String master_openid) {
        return replyMapper.getMyReplyByMasterOpenid(master_openid);
    }

    @Override
    public Integer ReplyNotice(String openid) {
        return replyMapper.ReplyNotice(openid);
    }
}
