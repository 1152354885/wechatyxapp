package com.me.test.controller;
import com.alibaba.fastjson.JSON;
import com.me.test.pojo.PayMent;


import com.me.test.service.PayMentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

//@Controller 控制层需要的注解
//@RestController 使用这个也是可以的，但是使用后他里面所有请求返回的都是字符串！
//一般只需要作为接口放回JSON格式数据的话推荐使用@RestController
//@Controller这个是可以与Thymeleaf模板引擎使用时可以返回一个页面的
@Controller
//@RequestMapping指定路径名
//@RequestMapping("/test")用这个来指定路径也是可以的
@RequestMapping(value = "/api/test")
public class PayMentController {
    @Autowired
    private PayMentService payMentService;
    //Get请求
    @GetMapping(value = "queryAll")
    //@ResponseBody 注释后表示放回的是字符串
    @ResponseBody
    public String queryAll(){
        List<PayMent> payMentList = payMentService.queryAll();
        return JSON.toJSONString(payMentList);
    }
    //使用了RestFull风格
    @GetMapping("queryById/{id}")
    @ResponseBody
    public String query(@PathVariable(value = "id")Integer id){
        PayMent payMent = payMentService.queryById(id);
        List<PayMent> payMentList = new ArrayList<>();
        payMentList.add(payMent);
        return JSON.toJSONString(payMentList);
    }
    //post请求
    //@RequestBody 表示接受请求的JSON格式的数据
    @PostMapping(value = "add")
    @ResponseBody
    public String add(@RequestBody PayMent payMent){
        System.out.println("添加"+payMent);
        payMentService.add(payMent);
        return "添加OK";
    }

    @DeleteMapping(value = "Delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id")Integer id){
        payMentService.delete(id);
        return "删除成功";
    }

    //Put请求
   // @PostMapping(value = "update")
   @PutMapping("update/{id}")
    @ResponseBody
    public String update(@PathVariable("id")Integer id,
                         @RequestBody PayMent payMent){
        payMent.setId(id);
        payMentService.update(payMent);
        return "修改成功";
    }
//    @RequestMapping("login")
//    @ResponseBody
//    public  boolean login (String serial, String result){
//        System.out.println ( "微信小程序调用接口！！！用户名:" + serial + "密码:" + result );
//        boolean login = payMentService.login ( serial, result );
//        if (login) {
//            return true;
//        }
//        return false;
//    }
    //查询byOpenid

}
