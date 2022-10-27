package com.me.test.controller;

import com.me.test.pojo.ActiVity;
import com.me.test.service.ActiVityService;
import com.me.test.utils.DateTimeTransferUtil;
import com.me.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("")指定路径名

@RequestMapping(value = "/api/act")
public class ActiVityController {

    @Autowired
    private ActiVityService actiVityService;
    @Autowired
    private RedisUtil redisUtil;

    //get 请求查询所有活动
    @GetMapping(value = "queryAll")
    @ResponseBody
    public List<ActiVity> queryAll() {
        List<ActiVity> ActiVityList = actiVityService.queryAll();
        return ActiVityList;
    }

    //查询byid带评论 两表联查
    @GetMapping("query/{id}")
    @ResponseBody
    public List<ActiVity> query(@PathVariable(value = "id") Integer id) {
        ActiVity actiVity = actiVityService.getDetailAndReply(id);
        List<ActiVity> actiVityList = new ArrayList<>();
        // redis 实现访问量
        boolean hasKey = redisUtil.hasKey(String.valueOf(id));

        if (hasKey) {
            System.out.print("存在");
            redisUtil.incr(String.valueOf(id), 1);
        } else {
            System.out.print("不存在");
            redisUtil.set(String.valueOf(id), 0);
            System.out.println("Redis及数据库初始化完毕");

        }
        actiVity.setCount(String.valueOf(redisUtil.get(String.valueOf(id))));
        actiVityList.add(actiVity);
        System.out.println("访问量：" + redisUtil.get(String.valueOf(id)));
        return actiVityList;
    }

    //删除byID 通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：
    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id) {
        actiVityService.delete(id);
        return "删除成功";
    }

    //新增活动
    //post请求
    //@RequestBody 表示接受请求的JSON格式的数据
    @PostMapping(value = "add")
    @ResponseBody
    public String add(@RequestBody ActiVity actiVity) {
        System.out.println("添加" + actiVity);
        String publish_time = DateTimeTransferUtil.getFormatTime();//添加发布时间
        System.out.println("发布时间：" + publish_time);
        actiVity.setPublish_time(publish_time);
        actiVityService.add(actiVity);
        return "添加OK";
    }

    //编辑一条活动
    //Put请求
    // @PostMapping(value = "update")
    @PutMapping("update/{id}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id,
                         @RequestBody ActiVity actiVity) {
        actiVity.setId(id);
        String publish_time = DateTimeTransferUtil.getFormatTime();
        //更新发布时间
        System.out.println("更新时间：" + publish_time);
        actiVity.setPublish_time(publish_time);
        actiVityService.update(actiVity);
        return "修改成功";
    }

    //查询byopenid,用于我的发布
    @GetMapping("person/{open_id}")
    @ResponseBody
    public List<ActiVity> queryByOpenId(@PathVariable(value = "open_id") String open_id) {
        List<ActiVity> actiVityList = actiVityService.queryByOpenId(open_id);
        //新建一个名字为actiVityList的数组列表，数组元素均由ActiVity类型数据组成。
        return actiVityList;
    }

    //模糊查询by活动正文和活动类型和地址
    @GetMapping("/search")
    @ResponseBody
    public List<ActiVity> search(@RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println("关键词" + keyword);
        List<ActiVity> ActiVityList = actiVityService.searchByKeyWord(keyword);
        return ActiVityList;
    }

    //批量删除活动，后台管理员用
    @DeleteMapping(value = "/batchDelete")
    @ResponseBody
    public String batchDelete(@RequestParam(value = "Ids") String Ids) {
        String[] IdS = Ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (String str : IdS) {
            list.add(Integer.parseInt(str));
        }
        System.out.println("获取" + list);
        actiVityService.batchDelete(list);
        return "ok";
    }

    @GetMapping("/condition")
    @ResponseBody
    public List<ActiVity> queryByConditionMap(@RequestParam(value = "act_type", required = false) String act_type,
                                              @RequestParam(value = "act_time", required = false) String act_time,
                                              @RequestParam(value = "act_addr", required = false) String act_addr) {
        act_type = act_type.length() == 0 ? null : act_type;
        act_time = act_time.length() == 0 ? null : act_time;
        act_addr = act_addr.length() == 0 ? null : act_addr;
//        System.out.println("类型" + act_type + "时间" + act_time + "地址" + act_addr);
        Map<String, Object> map = new HashMap<>();
        map.put("act_type", act_type);
        map.put("act_time", act_time);
        map.put("act_addr", act_addr);
        List<ActiVity> list = actiVityService.queryByConditionMap(map);
        System.out.println(list);
        return list;
    }


    //查询谁收藏了我
    @GetMapping("/myfollowers")
    @ResponseBody
    public List<ActiVity> getWhoFollowMe(@RequestParam("openid") String open_id) {
        List<ActiVity> actiVityList = actiVityService.getWhoFollowMe(open_id);
        return actiVityList;
    }

}
