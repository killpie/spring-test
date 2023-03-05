package com.shuozhuoding.springtest.example.pipeline.usecase.inttostring;

import com.shuozhuoding.springtest.example.pipeline.component.Channel;
import com.shuozhuoding.springtest.example.pipeline.component.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

public class IncrChannel extends Channel<Integer,Integer> {
    @Override
    protected Integer doExecute(Integer o) {
        return o+1;
    }

    @Override
    public String getName() {
        return "IncrChannel";
    }

    @Override
    public Collection<Component> getDownStreams() {
        return Collections.singleton(new StringChannel());
    }

    @Override
    public void init(String config) {

    }
}
