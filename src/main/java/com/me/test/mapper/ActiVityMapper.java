package com.me.test.mapper;


import com.me.test.pojo.ActiVity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ActiVityMapper {
    /**
     * 查询全部数据
     *
     * @return
     */
    List<ActiVity> queryAll();

    void delete(Integer id);

    void add(ActiVity actiVity);

    void update(ActiVity actiVity);

    List<ActiVity> queryByOpenId(String open_id);

    ActiVity queryById(Integer id);

    List<ActiVity> searchByKeyWord(String keyword);

    void batchDelete(List<Integer> list);

    ActiVity getDetailAndReply(@Param("id") Integer id);

    List<ActiVity> queryByConditionMap(Map<String, Object> conditionMap);

    List<ActiVity> getActivityAndApplyByStep(String open_id);

    ActiVity getApplyAndActivityByStepTwo(@Param("act_id")Integer id);

    ActiVity getSucceedApplyAndActivityByStepTwo(@Param("act_id")Integer id);

    List<ActiVity> getWhoFollowMe(@Param("open_id") String open_id);

}
