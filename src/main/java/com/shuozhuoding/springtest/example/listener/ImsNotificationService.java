package com.shuozhuoding.springtest.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: killpie
 * @Date: 2023/8/27 17:15
 * @Description: ImsNotificationService
 */
@Slf4j
@Service
public class ImsNotificationService implements IImsNotificationService{
    @Override
    public void sendMsg(String orderId, String msg) {
        log.info("发送消息：orderId={}, msg={}", orderId, msg);
    }
}
