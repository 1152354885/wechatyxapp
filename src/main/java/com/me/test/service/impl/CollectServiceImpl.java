package com.me.test.service.impl;

import com.me.test.mapper.CollectMapper;
import com.me.test.pojo.ActiVity;
import com.me.test.pojo.Collect;
import com.me.test.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Override
    public List<ActiVity> getCollectActivity(String openid) {
        return collectMapper.getCollectActivity(openid);
    }

    @Override
    public void addCollect(Collect collect) {
         collectMapper.addCollect(collect);
    }

    @Override
    public Collect queryByCollectcore(String collectcore) {
        return collectMapper.queryByCollectcore(collectcore);
    }

    @Override
    public Integer countCollect(Integer act_id) {
        return  collectMapper.countCollect(act_id);
    }

    @Override
    public void deleteCollect(Integer collect_id) {
        collectMapper.deleteCollect(collect_id);
    }
}
