package com.web3horizen.hoa.framework;

public class Route {
    protected String method;
    protected String path;
    protected Class<?> controller;
    protected String action;

    public Route(String method, String path, Class<?> controller, String action) {
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

    public Class<?> getController() {
        return controller;
    }

    public String getAction() {
        return action;
    }
}
