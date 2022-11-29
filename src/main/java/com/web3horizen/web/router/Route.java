package com.web3horizen.web.router;

import com.web3horizen.web.Controller;

public class Route {
    protected String method;
    protected String path;
    protected Class<? extends Controller> controller;
    protected String action;

    public Route(String method, String path, Class<? extends Controller> controller, String action) {
        this.method = method;
        this.path = path;
        this.controller = controller;
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Class<? extends Controller> getController() {
        return controller;
    }

    public String getAction() {
        return action;
    }
}
