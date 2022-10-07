package com.me.test.controller;

import com.me.test.pojo.Notice;
import com.me.test.service.ApplyService;
import com.me.test.service.ReplyService;
import com.me.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//请求通知类
@Controller
//@RequestMapping("")指定路径名

@RequestMapping(value = "/api/notice")
public class NoticeController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private RedisUtil redisUtil;
    
    @GetMapping("query/{openid}")
    @ResponseBody
    public Notice ReplyNotice(@PathVariable(value = "openid") String openid,
                              @RequestParam("states") String states) {
        // String usermessageKey = openid+"message";
        boolean hasKey = redisUtil.hasKey(openid + "messagetotal");
        Notice notice = new Notice();
        if (hasKey) {
            System.out.print("存在");
            Integer total = replyService.ReplyNotice(openid);
//            Integer total2 = applyService.applyNotice(openid);
//            System.out.println(total2);
            //         redisUtil.set(String.valueOf(openid + "applytotal"), total2);
            redisUtil.set(openid + "messagetotal", total);
            notice.setMassageView(total - (Integer.valueOf((String) redisUtil.get(openid + "messagelast"))));
            notice.setApplyView(applyService.applyNotice(openid, states));
        } else {
            System.out.print("不存在");
            redisUtil.set(openid + "messagetotal", 0);
            redisUtil.set(openid + "messagelast", 0);
//            redisUtil.set(String.valueOf(openid + "applytotal"), 0);
//            redisUtil.set(String.valueOf(openid + "applylast"), 0);
            System.out.println("Redis及数据库初始化完毕");
            
        }
        return notice;
    }
    
    @PostMapping("clear/massageView/{openid}")
    @ResponseBody
    public String ClearmassageView(@PathVariable(value = "openid") String openid) {
        redisUtil.set(openid + "messagelast", (Integer.valueOf((String) redisUtil.get(openid + "messagetotal"))));
        return "已清除信息";
    }
    // 废弃接口
//    @PostMapping("clear/applyView/{openid}")
//    @ResponseBody
//    public String ClearapplyView(@PathVariable(value = "openid") String openid) {
//
//        redisUtil.set(String.valueOf(openid + "applylast"),  redisUtil.get(openid + "applytotal"));
//        return "已清除申请信息";
//    }
}
