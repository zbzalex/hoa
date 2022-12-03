package com.web3horizen.web.framework;

import com.web3horizen.web.framework.mvc.Result;

import java.util.List;

public interface Application {
    List<Route> getRoutes();

    void addRoute(Route route);

    Result handleRequest(Session session, Request request, Response response);

    void initModule();
}
