package com.me.test.service.impl;

import com.me.test.mapper.SecondReplyMapper;
import com.me.test.pojo.SecondReply;
import com.me.test.service.SecondReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondReplyServiceImpl implements SecondReplyService {
    @Autowired
    private SecondReplyMapper secondReplyMapper;
    @Override
    public void deleteSecondReply(Integer id) {

        secondReplyMapper.deleteSecondReply(id);

    }

    @Override
    public List<SecondReply> getMySecondReplyByOpenid(String master_replyopenid) {
        return secondReplyMapper.getMySecondReplyByOpenid(master_replyopenid);
    }

    @Override
    public void addSecondReply(SecondReply secondReply) {
        secondReplyMapper.addSecondReply(secondReply);
    }
}
