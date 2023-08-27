package com.shuozhuoding.springtest.example.listener;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;


public interface IImsNotificationService {
    void sendMsg(String orderId, String msg);
}
