package com.me.test.service;

import com.me.test.pojo.ActiVity;
import com.me.test.pojo.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {

    public List<ActiVity> getCollectActivity(String openid);

    public void addCollect(Collect collect);

    public Collect queryByCollectcore(String collectcore);

    public Integer countCollect(Integer act_id);

    void  deleteCollect(@Param("collect_id") Integer  id);

}

