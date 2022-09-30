package com.me.test.mapper;

import com.me.test.pojo.ActiVity;
import com.me.test.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectMapper {
    public List<ActiVity> getCollectActivity(@Param("openid") String openid);

    public void addCollect(Collect collect);

    public Collect queryByCollectcore(String collectcore);

    public Integer countCollect(Integer act_id);

    void  deleteCollect(@Param("collect_id") Integer  collect_id);
}
