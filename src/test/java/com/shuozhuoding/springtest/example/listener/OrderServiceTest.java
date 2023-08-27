package com.shuozhuoding.springtest.example.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Auther: killpie
 * @Date: 2023/8/27 18:12
 * @Description:
 */
@ServletComponentScan
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Test
    public void test() throws InterruptedException {
        orderService.autoCancel("123");
        Thread.sleep(10000);
    }
}
