package com.me.test.mapper;

import com.me.test.pojo.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplyMapper {
  public String  queryByActId(Integer id);

  void  updateStateById(Apply apply);

  public Apply queryByApplyId(Integer apply_id);

  public Apply queryByApplyCore(String reply_core);

  void addApply(Apply apply);

  void  updateCheckById(Integer apply_id);

  public  Integer  applyNotice(@Param("openid")String openid,@Param("states")String states);

  List<Apply> getApplyAndActivityByStep(String master_openid,String states1,String states2);

  List<Apply> getMyApplyAndActivityByStep(String apply_openid,String states1,String states2);//查我的申请

  Apply  getSucceedApplyAndActivityByStep(Integer apply_id);
}
