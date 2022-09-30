package com.me.test.controller;

import com.alibaba.fastjson.JSON;
import com.me.test.pojo.Apply;
import com.me.test.pojo.PayMent;
import com.me.test.pojo.UserInfo;
import com.me.test.service.ActiVityService;
import com.me.test.service.ApplyService;
import com.me.test.utils.DateTimeTransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("")指定路径名

@RequestMapping(value = "/api/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @PostMapping ("/update")
    @ResponseBody
    public String update(@RequestParam( "apply_id" ) Integer apply_id,
                         @RequestParam("states") String states) {

        System.out.println(apply_id + states);
        Apply apply = applyService.queryByApplyId(apply_id);
        apply.setStates(states);
        apply.setAgree_time(DateTimeTransferUtil.getFormatTime());//更新同意时间
        applyService.updateStateById(apply);
        return "修改成功";
    }
    @GetMapping("/query/{id}")
    @ResponseBody
    public String  queryByApplyId(@PathVariable(value = "id") Integer apply_id){

         Apply apply =  applyService.queryByApplyId(apply_id);
         List<Apply> applyList = new ArrayList<>();
         applyList.add(apply);
         return  JSON.toJSONString(applyList);
    }

    //post请求
    //@RequestBody 表示接受请求的JSON格式的数据
    @PostMapping(value = "/add")
    @ResponseBody
    public String addApply(@RequestBody Apply apply) {
        Apply apply1 = new Apply();
        apply1 = applyService.queryByApplyCore(apply.getApply_openid()+apply.getAct_id());//生成唯一标识
        if ( apply1 == null ) {
            System.out.println("新申请");
            apply.setApplication_time(DateTimeTransferUtil.getFormatTime());
            apply.setStates("待审核");
            apply.setApply_core(apply.getApply_openid()+apply.getAct_id());
            applyService.addApply(apply);
        }else {
            System.out.println("已申请");

            return  "已经申请过了";
        }
        return "申请成功";
    }

    //先通过master_openid查询申请 再用act_id查询活动
    @GetMapping(value = "/other")
    //@ResponseBody 注释后表示放回的是字符串
    @ResponseBody
    public String getApplyAndActivityByStep(@RequestParam("master_openid") String master_openid,
                                            @RequestParam("states1")String states1,
                                            @RequestParam("states2")String states2){
        System.out.println(master_openid+states1+states2);
        List<Apply> applyList = applyService.getApplyAndActivityByStep(master_openid,states1,states2);
        return JSON.toJSONString(applyList);
    }

    @GetMapping(value = "/person")
    //@ResponseBody 注释后表示放回的是字符串
    @ResponseBody
    public String getMyApplyAndActivityByStep(@RequestParam("apply_openid") String apply_openid,
                                              @RequestParam("states1")String states1,
                                              @RequestParam("states2")String states2) {
        System.out.println(apply_openid);
        List<Apply> applyList = applyService.getMyApplyAndActivityByStep(apply_openid,states1,states2);
        return JSON.toJSONString(applyList);
    }
    @GetMapping(value = "/success")
    //@ResponseBody
    @ResponseBody
    public Apply getSucceedApplyAndActivityByStep(@RequestParam("apply_id") Integer apply_id) {




        return  applyService.getSucceedApplyAndActivityByStep(apply_id);
    }
    @GetMapping(value = "/otherLink")
    //@ResponseBody
    @ResponseBody
    public Apply getOtherLink(@RequestParam("apply_id") Integer apply_id) {




        return  applyService.queryByApplyId(apply_id);
    }
}
