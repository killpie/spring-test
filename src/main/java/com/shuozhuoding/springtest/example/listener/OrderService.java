package com.shuozhuoding.springtest.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Auther: killpie
 * @Date: 2023/8/27 18:10
 * @Description: OrderService
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 订单超时关闭
     */
    public void autoCancel(String orderId) {
        log.info("订单超时关闭");
        // 发布事件
        applicationContext.publishEvent(new OrderCancelEvent(this, orderId));

        applicationContext.publishEvent(new OrderCreateEvent(this, orderId));
        log.info("订单超时关闭完成");
    }
}
