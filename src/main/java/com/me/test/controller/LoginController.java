package com.me.test.controller;

import com.alibaba.fastjson.JSON;
import com.me.test.pojo.UserInfo;
import com.me.test.service.UserInfoService;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
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


        @PostMapping("/api/login")
        @ResponseBody
        public  String login ( @RequestBody User user){
            System.out.println(user);
            System.out.println ( "管理员调用接口！！！用户名:" + user.getUsername() + "密码:" + user.getPassword() );
            boolean login = userInfoService.login ( user.getUsername(), user.getPassword() );
            if (login) {
                return "登录成功";
            }
            return "登录失败";
        }

    @GetMapping(value = "queryAll")
    //@ResponseBody 注释后表示放回的是字符串
    @ResponseBody
    public List<UserInfo> queryAll(){
        List<UserInfo> userInfoList = userInfoService.queryAll();
        return userInfoList;
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


