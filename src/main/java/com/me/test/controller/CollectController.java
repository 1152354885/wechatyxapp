package com.me.test.controller;

import com.me.test.pojo.ActiVity;
import com.me.test.pojo.Collect;
import com.me.test.service.ActiVityService;
import com.me.test.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("")指定路径名

@RequestMapping(value = "/api/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private ActiVityService actiVityService;
    
    @GetMapping("/query")
    @ResponseBody
    public List<ActiVity> query(@RequestParam(value = "openid") String openid) {
        List<ActiVity> actiVityList = collectService.getCollectActivity(openid);
        return actiVityList;
    }
    
    @PostMapping(value = "/add")
    @ResponseBody
    public String add(@RequestBody Collect collect) {
        System.out.println("收藏" + collect);
        Collect collect1 = collectService.queryByCollectcore(collect.getOpenid() + collect.getAct_id());
        if (collect1 == null) {
            collect.setCollectcore(collect.getOpenid() + collect.getAct_id());
            
            collectService.addCollect(collect);
            ActiVity actiVity = actiVityService.queryById(collect.getAct_id());
            actiVity.setCollect_count(collectService.countCollect(collect.getAct_id()));
            System.out.println("次数" + actiVity.getCollect_count());
            
            actiVityService.update(actiVity);
        } else {
            return "收藏过了";
        }
        return "收藏成功";
        
    }
    
    //测试用
    @GetMapping("test/count/{id}")
    @ResponseBody
    public Integer countCollect(@PathVariable(value = "id") Integer act_id) {
        return collectService.countCollect(act_id);
        
    }
    
    //监听收藏的状态
    @GetMapping("/count/status/{collectcore}")
    @ResponseBody
    public Boolean status(@PathVariable(value = "collectcore") String collectcore) {
        Collect collect = collectService.queryByCollectcore(collectcore);
        return collect != null;
    }
    
    @DeleteMapping("delete/{collectcore}")
    @ResponseBody
    public String deleteCollect(@PathVariable(value = "collectcore") String collectcore) {
        Collect collect = collectService.queryByCollectcore(collectcore);
        ActiVity actiVity = actiVityService.queryById(collect.getAct_id());
        actiVity.setCollect_count(collectService.countCollect(collect.getAct_id()) - 1);
        actiVityService.update(actiVity);// 刷新收藏次数
        collectService.deleteCollect(collect.getCollect_id());
        return "删除成功";
    }
}
