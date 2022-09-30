package com.me.test.service.impl;


import com.me.test.mapper.ApplyMapper;
import com.me.test.pojo.Apply;
import com.me.test.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyMapper applyMapper;
    @Override
    public void updateStateById(Apply apply) {
        applyMapper.updateStateById(apply);
    }

    @Override
    public Apply queryByApplyId(Integer apply_id) {
        return applyMapper.queryByApplyId(apply_id);
    }

    @Override
    public void addApply(Apply apply) {
        applyMapper.addApply(apply);
    }

    @Override
    public Integer applyNotice(String openid,String states) {
        return applyMapper.applyNotice(openid,states) ;
    }

    @Override
    public Apply queryByApplyCore(String reply_core) {
        return  applyMapper.queryByApplyCore(reply_core);
    }

    @Override
    public void updateCheckById(Integer apply_id) {
        applyMapper.updateCheckById(apply_id);
    }

    @Override
    public List<Apply> getApplyAndActivityByStep(String master_openid,String states1,String states2) {
        return  applyMapper.getApplyAndActivityByStep(master_openid,states1,states2);
    }

    @Override
    public List<Apply> getMyApplyAndActivityByStep(String apply_openid,String states1,String states2) {
        return applyMapper.getMyApplyAndActivityByStep(apply_openid,states1,states2);
    }

    @Override
    public Apply getSucceedApplyAndActivityByStep(Integer apply_id) {
        return applyMapper.getSucceedApplyAndActivityByStep(apply_id);
    }


}
