package com.web3horizen.web.framework;

import java.util.List;
import java.util.regex.Pattern;

public class Matcher {
    protected List<Route> routes;

    public Matcher(List<Route> routes) {
        this.routes = routes;
    }

    public Route match(Request request) {
        for (Route route : routes) {

            String path = route.getPath();
            if (!path.endsWith("/")) {
                path += "/";
            }

            if (path.endsWith("/")) {
                path += "?";
            }

            String re = "^" + path + "$";

            Pattern p = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
            java.util.regex.Matcher matcher = p.matcher(request.getPath());
            if (matcher.find() && (route.getMethod().equals("*") || route.getMethod().equals(request.getMethod()))) {
                return route;
            }
        }
        return null;
    }
}
