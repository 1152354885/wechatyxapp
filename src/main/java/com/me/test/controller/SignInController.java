package com.me.test.controller;

import ch.qos.logback.core.util.CachingDateFormatter;
import com.alibaba.fastjson.JSON;
import com.me.test.pojo.ActiVity;
import com.me.test.pojo.PayMent;
import com.me.test.pojo.SignIn;
import com.me.test.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping指定路径名
//@RequestMapping("/test")用这个来指定路径也是可以的
@RequestMapping(value = "/api/sign")
public class SignInController {
    @Autowired
    private SignInService signInService;

    //post请求
    //签到
    @PostMapping(value = "/signIn")
    @ResponseBody
    public String add(@RequestParam String openid) {
        System.out.println(openid);
        LocalDate date = LocalDate.now(); // get the current date
        SignIn signIn = signInService.querySignInById(openid);
        System.out.println("是否为已签到用户" + signIn);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(date.format(formatter));
        String today = date.format(formatter);
        int point = 10;
        int day = 1;
        if (signIn == null) {
            SignIn newsign=new SignIn();
            newsign.setOpen_id(openid);
            newsign.setLast_sign_time(date.format(formatter));//获取签到时间
            newsign.setPoints(point);
            newsign.setDays(day);
            signInService.add(newsign);
          //对比日期字符串
        } else if(today.equals(signIn.getLast_sign_time()) == true) {

            return "已经签到啦！";
        }else {
            System.out.println(today.equals(signIn.getLast_sign_time()));
            System.out.println("老用户");
            signIn.setLast_sign_time(date.format(formatter));//更新签到时间
            System.out.println(signIn.getPoints() + point);
            signIn.setPoints(signIn.getPoints() + point);
            signIn.setDays(signIn.getDays() + day);
            signInService.updateSignIn(signIn);

        }

        return "签到OK";
    }
    @GetMapping("/person")
    @ResponseBody
    public String queryByOpenId(@RequestParam("open_id") String openid){
        SignIn signIn=signInService.queryByOpenId(openid);
        System.out.println(signIn);
        if (signIn != null ){
            List<SignIn> signInList = new ArrayList<>();
            signInList.add(signIn);
            return JSON.toJSONString(signInList);
        }else {

            return  "新用户签到领好礼";
        }
    }
}
