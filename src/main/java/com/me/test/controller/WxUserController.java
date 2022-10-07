package com.me.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.me.test.pojo.WxUser;
import com.me.test.service.impl.WxUserServiceImpl;
import com.me.test.utils.DateTimeTransferUtil;
import com.me.test.utils.IdWorker;
import com.me.test.utils.JwtUtil;
import com.me.test.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2022/8/31 13:10
 */
@RestController
@RequestMapping(value = "/api")
public class WxUserController {
    @Autowired
    private final WxUserServiceImpl wxUserService;
    @Autowired
    private final IdWorker idWorker;
    @Autowired
    private final JwtUtil jwtUtil;
    
    
    public WxUserController(WxUserServiceImpl wxUserService, IdWorker idWorker, JwtUtil jwtUtil) {
        this.wxUserService = wxUserService;
        this.idWorker = idWorker;
        this.jwtUtil = jwtUtil;
    }
    
    /**
     * 新老用户授权登录
     *
     * @param
     * @return
     */
    @PostMapping("/wx/login")
    public String login(@RequestParam String code,
                        @RequestParam String rawData) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        System.out.println(openid + "测试OPID左边，密钥：" + sessionKey);
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)===>无法实现暂时注释
//               String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
//        if (!signature.equals(signature2)) {
//            return new CommonResult< >(500, "NO",null);
//        }
        
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        System.out.println(openid);
        System.out.println(wxUserService.queryWxUserById(openid));
        WxUser wxuser = wxUserService.queryWxUserById(openid);
        System.out.println(wxuser);
        
        // IDworker生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = idWorker.nextId().toString();
        System.out.println("skey===>" + skey);
        
        if (wxuser == null) {
            //是新用户则将信息存入
            System.out.println("新用户");
            WxUser newUser = new WxUser();
            newUser.setOpen_id(openid);
            newUser.setSkey(skey);
            newUser.setCreate_time(DateTimeTransferUtil.getFormatTime());
            System.out.println("时间1：" + DateTimeTransferUtil.getFormatTime());
            newUser.setLast_visit_time(DateTimeTransferUtil.getFormatTime());
            System.out.println("时间2：" + DateTimeTransferUtil.getFormatTime());
            newUser.setSession_key(sessionKey);
            newUser.setCity(rawDataJson.getString("city"));
            newUser.setCountry(rawDataJson.getString("country"));
            newUser.setProvince(rawDataJson.getString("province"));
            newUser.setGender(rawDataJson.getString("gender"));
            newUser.setAvatar_url(rawDataJson.getString("avatarUrl"));
            newUser.setNick_name(rawDataJson.getString("nickName"));
            wxUserService.addWxUser(newUser);
            
        } else {
            //老用户，更新登陆时间
            System.out.println(" 老用户");
            //  wxuser.setLast_visit_time(DateTimeTransferUtil.getNowTimeStamp());
            //重置会话skey
            wxuser.setSkey(skey);
            //重置最后登录时间
            wxuser.setLast_visit_time(DateTimeTransferUtil.getFormatTime());
            wxUserService.updateWxUser(wxuser);
        }
        //使用token生成工具，生成带有openid和skey的token
        String token = jwtUtil.createJWT(skey, openid);
        
        //return new CommonResult<>(200, "OK", token);
        return openid;
    }
    
    //点击查看登录用户信息
    @GetMapping(value = "wx/query/person_activity/{openid}")
    @ResponseBody
    public List<WxUser> queryWxUserAndActivityByOpenID(@PathVariable("openid") String openid) {
        System.out.println(openid);
        WxUser wxUser = wxUserService.queryWxUserAndActivityByOpenID(openid);
        List<WxUser> wxUserList = new ArrayList<>();
        wxUserList.add(wxUser);
        return wxUserList;
    }
    
    //编辑用户资料
    @PutMapping(value = "wx/query/edit/{openid}")
    @ResponseBody
    public String EditWxUser(@PathVariable("openid") String open_id,
                             @RequestBody WxUser wxUser) {
        wxUser.setOpen_id(open_id);
        wxUserService.EditWxUser(wxUser);
        return "修改成功";
    }
    
    //查询编辑
    @GetMapping(value = "wx/query/person/{openid}")
    @ResponseBody
    public List<WxUser> queryWxUserByOpenID(@PathVariable("openid") String openid) {
        System.out.println(openid);
        WxUser wxUser = wxUserService.queryWxUserById(openid);
        List<WxUser> wxUserList = new ArrayList<>();
        wxUserList.add(wxUser);
        return wxUserList;
    }
}
