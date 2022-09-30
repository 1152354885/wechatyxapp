package com.me.test.controller;

import com.alibaba.fastjson.JSON;
import com.me.test.pojo.UserInfo;
import com.me.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * 项目没用到此
 *
 */

@RestController
    public class LoginController {

        @Autowired
        private UserInfoService userInfoService;


        @RequestMapping("login")
        public  boolean login (String username, String password){
            System.out.println(username+password);
            System.out.println ( "微信小程序调用接口！！！用户名:" + username + "密码:" + password );
            boolean login = userInfoService.login ( username, password );
            if (login) {
                return true;
            }
            return false;
        }

    @GetMapping(value = "queryAll")
    //@ResponseBody 注释后表示放回的是字符串
    @ResponseBody
    public String queryAll(){
        List<UserInfo> userInfoList = userInfoService.queryAll();
        return JSON.toJSONString(userInfoList);
    }
    @DeleteMapping(value = "Delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id")Integer id){
        userInfoService.delete(id);
        return "删除成功";
    }
    @ResponseBody
    @RequestMapping(value ="/UsersSave", method = RequestMethod.POST )
    public Map<String,Object> UsersSave(@RequestParam Map<String, Object> value){
        Map<String,Object> map=new HashMap<String,Object>();
        UserInfo u = new UserInfo();
        System.out.println(value.toString());
        u.setUsername(value.get("username").toString());
        u.setPassword(value.get("password").toString());
        System.out.println(u);
       // int r=users.UsersSave(u);
        int r=userInfoService.add(u);
        if(r>0){
            map.put("code",r);
        }else{
            map.put("code",0);
        }
        return map;
    }


    @PutMapping("update/{id}")
    @ResponseBody
    public String update(@PathVariable("id")Integer id,
                         @RequestBody UserInfo userInfo){
        userInfo.setId(id);
        userInfoService.update(userInfo);
        return "修改成功";
    }


    }


