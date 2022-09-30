package com.me.test.handler;


//import com.media.common.utils.DateUtils;
//import com.media.common.utils.RedisUtil;
//import com.media.picture.domain.MamPictureView;
//import com.media.picture.service.IMamPictureViewService;
import com.me.test.pojo.ActiVity;
import com.me.test.service.ActiVityService;
import com.me.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 **/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ListenHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ActiVityService actiVityService;

    public ListenHandler(){
        System.out.println("开始初始化");
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("Redis及数据库开始初始化");
//        //插入一条空数据
//       // MamPictureView mamPictureView = new MamPictureView();
//                ActiVity actiVity = new ActiVity();
////        mamPictureView.setViewNum(Long.valueOf(0));
//                actiVity.setCount(Long.valueOf(0));
////       int viewId = iMamPictureViewService.insertMamPictureView(mamPictureView);
//                int viewId = actiVityService.updateCount(actiVity).getId();
//                redisUtil.set("pageA_id", viewId);
//                redisUtil.set("pageA_count", 0);
//                System.out.println("Redis及数据库初始化完毕");
//
//    }
}

