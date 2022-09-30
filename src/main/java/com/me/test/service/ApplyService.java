package com.me.test.service;

import com.me.test.pojo.Apply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyService {
    void  updateStateById(Apply apply);

    public Apply queryByApplyId(Integer apply_id);

    void  addApply(Apply apply);

    public  Integer  applyNotice(String openid,String states);

    public Apply queryByApplyCore(String reply_core);

    void  updateCheckById(Integer apply_id);

    List<Apply> getApplyAndActivityByStep(String master_openid,String states1,String states2);

    List<Apply> getMyApplyAndActivityByStep(String apply_openid,String states1,String states2);//查我的申请

    Apply   getSucceedApplyAndActivityByStep(Integer apply_id);
}
