package com.shuozhuoding.springtest.example.listener;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Auther: killpie
 * @Date: 2023/8/27 17:10
 * @Description:
 * * 自定义订单监听事件，继承了ApplicationEvent，并重载构造函数
 *  * <p>
 *  * 构造函数的参数可以任意指定，其中source参数指的是发生事件的对象，
 *  而第二个参数是我们自定义的注册事件对象，该对象可以在监听内被获取。
 */
@Getter
public class OrderCreateEvent extends ApplicationEvent {
    private String orderId;
    public OrderCreateEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }
}
