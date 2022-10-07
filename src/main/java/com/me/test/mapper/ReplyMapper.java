package com.me.test.mapper;

import com.me.test.pojo.Reply;
import com.me.test.pojo.SecondReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReplyMapper {
    public void add (Reply reply);

    void delete(Integer reply_id);

    public List<Reply>  queryByOpenId(String reply_openid);

    void   update(Reply reply);

    List<Reply> getMyReplyByMasterOpenid(@Param("master_openid")String master_openid);

//    List<Reply> getSecondReplybyOpenid(String master_openid);

    Integer ReplyNotice(@Param("openid")String openid);
}
