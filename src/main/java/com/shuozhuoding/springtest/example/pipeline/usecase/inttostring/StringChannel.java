package com.shuozhuoding.springtest.example.pipeline.usecase.inttostring;

import com.shuozhuoding.springtest.example.pipeline.component.Channel;
import com.shuozhuoding.springtest.example.pipeline.component.Component;

import java.util.Collection;
import java.util.Collections;

public class StringChannel extends Channel<Integer,String> {
    @Override
    protected String doExecute(Integer o) {
        return String.valueOf(o);
    }

    @Override
    public String getName() {
        return "StringChannel";
    }

    @Override
    public Collection<Component> getDownStreams() {
        return Collections.singleton(new ConsoleSink());
    }

    @Override
    public void init(String config) {

    }
}
