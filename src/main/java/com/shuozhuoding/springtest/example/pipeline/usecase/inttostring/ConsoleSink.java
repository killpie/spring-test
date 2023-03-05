package com.shuozhuoding.springtest.example.pipeline.usecase.inttostring;

import com.shuozhuoding.springtest.example.pipeline.component.Component;
import com.shuozhuoding.springtest.example.pipeline.component.Sink;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class ConsoleSink extends Sink<String,Void> {
    @Override
    protected Void doExecute(String o) {
        log.info(o);
        return null;
    }

    @Override
    public String getName() {
        return "ConsoleSink";
    }

    @Override
    public Collection<Component> getDownStreams() {
        return null;
    }

    @Override
    public void init(String config) {

    }
}
