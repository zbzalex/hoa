package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.mvc.Result;

public interface Application {
    void init(Class<?> moduleClass);

    Result handleRequest(Request request, Response response);

    void start();
}
