package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.mvc.Result;

public interface Application {
    void addRoute(Route route);

    Result handleRequest(Session session, Request request, Response response);

    void initModule();
}
