package com.me.test.mapper;

import com.me.test.pojo.SecondReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

public interface SecondReplyMapper {

    void deleteSecondReply(Integer sec_reply_id);

    List<SecondReply> getMySecondReplyByOpenid(String master_replyopenid);

    void addSecondReply (SecondReply secondReply);

}
