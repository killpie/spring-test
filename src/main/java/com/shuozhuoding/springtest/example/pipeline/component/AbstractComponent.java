package com.shuozhuoding.springtest.example.pipeline.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 组件抽象实现
 * @param <T> 输入
 * @param <R> 输出
 */
@Slf4j
public abstract class AbstractComponent<T,R> implements Component<T>{
    @Override
    public void execute(T o) {
        //当前组件执行,相当于执行入口是唯一的，方便做处理
        R r = doExecute(o);
        log.info("{} receive {} return {}",getName(),o,r);
        //获取下游组件开始执行
        Collection<Component> downStreams = getDownStreams();
        if (!CollectionUtils.isEmpty(downStreams)){
            downStreams.forEach((v)->v.execute(r));
        }
    }

    protected abstract R doExecute(T o);

    @Override
    public void startup() {
        log.info("{} 开始启动",getName());
        //启动下游组件
        Collection<Component> downStreams = getDownStreams();
        if (CollectionUtils.isEmpty(downStreams)){
            downStreams.forEach(Component::startup);
        }
        log.info("{} 启动完毕",getName());
    }

    @Override
    public void shutdown() {
        log.info("{} 开始关闭",getName());
        //关闭下游组件
        Collection<Component> downStreams = getDownStreams();
        if (CollectionUtils.isEmpty(downStreams)){
            downStreams.forEach(Component::shutdown);
        }
        log.info("{} 关闭完毕",getName());
    }
}
