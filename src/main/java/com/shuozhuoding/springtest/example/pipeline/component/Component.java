package com.shuozhuoding.springtest.example.pipeline.component;

import java.util.Collection;

/**
 * 组件
 * @param <T> 输入
 */
public interface Component<T> extends LifeCycle{
    /**
     *  组件名称
     * @return
     */
    String getName();

    /**
     *  获取下游组件
     * @return
     */
    Collection<Component> getDownStreams();

    /**
     *  执行
     */
    void execute(T o);
}
