package com.shuozhuoding.springtest.example.pipeline.usecase.inttostring;

import com.shuozhuoding.springtest.example.pipeline.component.Component;
import com.shuozhuoding.springtest.example.pipeline.component.Source;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;

/**
 * 数据输入来源
 */
@Slf4j
public class IntegerSource extends Source<Integer,Integer> {
    private int val = 0;
    @Override
    protected Integer doExecute(Integer o) {
        return o;
    }

    @Override
    public String getName() {
        return "IntegerSource";
    }

    @Override
    public Collection<Component> getDownStreams() {
    return Collections.singleton(new IncrChannel());
    }

    @Override
    public void init(String config) {
        log.info("{} {}",getName(),config);
        val = 1;
    }

    @Override
    public void startup() {
        super.startup();
        execute(val);
    }
}
