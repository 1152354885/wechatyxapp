package com.me.test.controller;

import com.alibaba.fastjson.JSON;
import com.me.test.pojo.*;
import com.me.test.service.ReplyService;
import com.me.test.service.SecondReplyService;
import com.me.test.utils.DateTimeTransferUtil;
import com.me.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping指定路径名
//@RequestMapping("/test")用这个来指定路径也是可以的
@RequestMapping(value = "/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private SecondReplyService secondReplyService;

    @Autowired
    private RedisUtil redisUtil;
    //post请求
    //@RequestBody 表示接受请求的JSON格式的数据
    @PostMapping(value = "add")
    @ResponseBody
    public String add(@RequestBody Reply reply){
        System.out.println("添加"+reply);
        String reply_time= DateTimeTransferUtil.getFormatTime();//添加发布时间
        reply.setReply_time(reply_time);
        replyService.add(reply);
        return "添加OK";
    }
    @DeleteMapping(value = "delete/{id}/{openid}")
    @ResponseBody
    public String delete(@PathVariable("id")Integer id,@PathVariable("openid")String openid){
        System.out.println(openid);
        redisUtil.decr(String.valueOf(openid + "messagelast"),1);
        System.out.println(redisUtil.get(String.valueOf(openid + "messagelast")));
        replyService.delete(id);
        return "删除成功";
    }
   //查询自己发的By openID
   @GetMapping("/person")
   @ResponseBody
   public String queryByOpenId(@RequestParam("open_id") String open_id){
        System.out.println(open_id);
       List<Reply> replyList=replyService.queryByOpenId(open_id);

       return JSON.toJSONString(replyList);
   }

    //Put请求
    // @PostMapping(value = "update")
    @PutMapping("update/{id}")
    @ResponseBody
    public String update(@PathVariable("id")Integer id,
                         @RequestBody Reply reply){
        reply.setReply_id(id);
        String reply_time=DateTimeTransferUtil.getFormatTime();
        //更新发布时间
        System.out.println("更新时间："+reply_time);
        reply.setReply_time(reply_time);
        replyService.update(reply);
        return "修改成功";
    }
    //查询我的回复bymaster_openid
    @GetMapping("/myreply")
    @ResponseBody
    public String getMyReplyByMasterOpenid(@RequestParam("master_openid")String master_openid){
        List<Reply> replyList  =   replyService.getMyReplyByMasterOpenid(master_openid);
        return JSON.toJSONString(replyList);
    }
    //回复我评论的评论
    @GetMapping("/mySecondReply")
    @ResponseBody
    public String StringgetMySecondReplyByOpenid(@RequestParam("master_replyopenid") String master_replyopenid){
     List<SecondReply> secondReplies = secondReplyService.getMySecondReplyByOpenid(master_replyopenid);
        return JSON.toJSONString(secondReplies);
    }
    //删除一条子评论
    @DeleteMapping(value = "delete/second/{id}/{openid}")
    @ResponseBody
    public String deleteSecondReply(@PathVariable("id")Integer sec_reply_id,@PathVariable("openid")String openid){
        System.out.println(openid);
        redisUtil.decr(String.valueOf(openid + "messagelast"),1);
        System.out.println(redisUtil.get(String.valueOf(openid + "messagelast")));
        secondReplyService.deleteSecondReply(sec_reply_id);
        return "删除成功";
    }
    //发布评论他人的评论
    @PostMapping(value = "/add/second")
    @ResponseBody
    public String addSecondReply (@RequestBody SecondReply secondReply){
        System.out.println("添加"+secondReply);
        secondReplyService.addSecondReply(secondReply);
        return "添加OK";
    }
}
