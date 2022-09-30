package com.me.test.service.impl;

import com.me.test.mapper.ActiVityMapper;
import com.me.test.pojo.ActiVity;
import com.me.test.pojo.PayMent;
import com.me.test.service.ActiVityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActiVityServiceImpl implements ActiVityService {
    @Autowired
    private ActiVityMapper actiVityMapper;
    @Override
    public List<ActiVity> queryAll() {
        return actiVityMapper.queryAll();
    }

    @Override
    public void delete(Integer id) {
        actiVityMapper.delete(id);
    }
    @Override
    public void add(ActiVity actiVity) {
        actiVityMapper.add(actiVity);
    }

    @Override
    public void update(ActiVity actiVity) {
        actiVityMapper.update(actiVity);
    }

    @Override
    public ActiVity queryById(Integer id) {
       return actiVityMapper.queryById(id);

    }

    @Override
    public List<ActiVity> queryByOpenId(String open_id) {
        return actiVityMapper.queryByOpenId(open_id);
    }


    @Override
    public List<ActiVity> searchByKeyWord(String keyword) {
        return actiVityMapper.searchByKeyWord(keyword);
    }

    @Override
    public void batchDelete(List<Integer> list) {
        actiVityMapper.batchDelete(list);
    }

    @Override
    public ActiVity getDetailAndReply(Integer id) {
        return actiVityMapper.getDetailAndReply(id);
    }

    @Override
    public List<ActiVity> queryByConditionMap(Map<String, Object> conditionMap) {

        return actiVityMapper.queryByConditionMap(conditionMap);
    }

    @Override
    public List<ActiVity> getActivityAndApplyByStep(String open_id) {
        return actiVityMapper.getActivityAndApplyByStep(open_id);
    }

    @Override
    public List<ActiVity> getWhoFollowMe(String open_id) {
        return actiVityMapper.getWhoFollowMe(open_id);
    }


}
