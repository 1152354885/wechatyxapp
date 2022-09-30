package com.me.test.service;



import com.me.test.pojo.ActiVity;
import com.me.test.pojo.PayMent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActiVityService {

    List<ActiVity> queryAll();

    void delete(Integer id);

    void add(ActiVity actiVity);

    void update(ActiVity actiVity);

    ActiVity queryById(Integer id);
    List<ActiVity> queryByOpenId(String open_id);;
    //模糊查询
    List<ActiVity> searchByKeyWord(String keyword);
    //批量删除
    void batchDelete(List<Integer> list);
    //查询详情和评论
    public ActiVity getDetailAndReply(Integer id);

    List<ActiVity> queryByConditionMap(Map<String,Object> conditionMap);

    List<ActiVity> getActivityAndApplyByStep(String open_id);

    List<ActiVity> getWhoFollowMe( String open_id);
}
