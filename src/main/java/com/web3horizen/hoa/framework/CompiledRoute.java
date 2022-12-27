package com.web3horizen.hoa.framework;

import java.util.Map;

public class CompiledRoute {
    private final String compiledPath;
    private final Route route;
    private final Map<String, String> params;

    public CompiledRoute(String compiledPath, Route route, Map<String, String> params) {
        this.compiledPath = compiledPath;
        this.route = route;
        this.params = params;
    }

    public String getCompiledPath() {
        return compiledPath;
    }

    public Route getRoute() {
        return route;
    }

    public Map<String, String> getParams() {
        return params;
    }

}
