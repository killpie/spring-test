package com.shuozhuoding.springtest.example.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Auther: killpie
 * @Date: 2023/8/27 17:14
 * @Description: MsgSendListener
 */
@Component
@Slf4j
public class MsgSendListener {

    @Resource
    @Qualifier("imsNotificationService")
    private IImsNotificationService imsNotificationService;

    /**
     * 订单超时关闭发送异步消息
     * @param event
     */
    @EventListener
    @Async
    public void sendMsgAfterAutoCancel(OrderCancelEvent event) {
        try {
            log.info("订单超时关闭发送异步消息");
            Thread.sleep(3000);
            imsNotificationService.sendMsg(event.getOrderId(),"订单超时关闭");
            log.info("订单超时关闭发送异步消息完成");
        }catch (Exception e){
            log.error("订单超时关闭发送异步消息异常",e);
        }

    }


    @EventListener
    @Async
    public void sendMsgAfterAutoCreate(OrderCreateEvent event) {
        try {
            log.info("订单创建发送异步消息");
            Thread.sleep(3000);
            imsNotificationService.sendMsg(event.getOrderId(),"订单创建");
            log.info("订单创建发送异步消息完成");
        }catch (Exception e){
            log.error("订单创建发送异步消息异常",e);
        }

    }
}
