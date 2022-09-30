package com.me.test.service;

import com.me.test.pojo.SecondReply;

import java.util.List;

public interface SecondReplyService {
        public void   deleteSecondReply(Integer id);

        List<SecondReply> getMySecondReplyByOpenid(String master_replyopenid);

        void addSecondReply (SecondReply secondReply);
}
