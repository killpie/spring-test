package com.shuozhuoding.springtest.example.pipeline.usecase.inttostring;

import com.shuozhuoding.springtest.example.pipeline.component.LifeCycle;
import com.shuozhuoding.springtest.example.pipeline.component.Source;

public class Pipeline implements LifeCycle {

    private Source source;

    public Pipeline(Source source) {
        this.source = source;
    }

    @Override
    public void init(String config) {
        source.init("");
    }

    @Override
    public void startup() {
        source.startup();
    }

    @Override
    public void shutdown() {
        source.shutdown();
    }

  public static void main(String[] args) {
    //
      Pipeline pipeline = new Pipeline(new IntegerSource());
      pipeline.init("");
      pipeline.startup();
      pipeline.shutdown();
  }
}
