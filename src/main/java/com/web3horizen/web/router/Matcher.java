package com.web3horizen.web.router;

import com.web3horizen.web.Request;

import java.util.List;
import java.util.regex.Pattern;

public class Matcher {
    protected List<Route> routes;

    public Matcher(List<Route> routes) {
        this.routes = routes;
    }

    public Route match(Request request) {
        for (Route route : routes) {
            Pattern p = Pattern.compile("^" + route.getPath() + "$", Pattern.CASE_INSENSITIVE);
            java.util.regex.Matcher matcher = p.matcher(request.getPath());
            if (matcher.find() && (route.getMethod().equals("*") || route.getMethod().equals(request.getMethod()))) {
                return route;
            }
        }
        return null;
    }
}
