package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.mvc.Result;

import java.util.List;

public interface Application {
    void setMainModuleClass(Class<?> moduleClass);

    void run();

    Result handleRequest(Session session, Request request, Response response);

    void terminate();

    List<Route> getRoutes();
}
