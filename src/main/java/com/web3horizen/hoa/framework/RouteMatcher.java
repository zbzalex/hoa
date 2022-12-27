package com.web3horizen.hoa.framework;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteMatcher {
    protected List<Route> routes;
    protected List<CompiledRoute> cachedRoutes = new ArrayList<>();

    public RouteMatcher(List<Route> routes) {
        this.routes = routes;
    }

    public CompiledRoute match(Request request) {
        for (Route route : routes) {
            CompiledRoute cachedRoute;
            if ((cachedRoute = getCachedRoute(route.getPath())) != null) {
                return cachedRoute;
            }

            String compiledPath = RouteCompiler.compile(route.getPath());
            Set<String> namedGroups = getNamedGroupCandidates(compiledPath);
            Pattern p = Pattern.compile(compiledPath, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(request.getPath());
            if (m.find() && (route.getMethod().equals("*") || route.getMethod().equals(request.getMethod()))) {
                Map<String, String> params = new HashMap<>();
                for (String namedGroup : namedGroups) {
                    params.put(namedGroup, m.group(namedGroup));
                }

                return new CompiledRoute(compiledPath, route, params);
            }

        }
        return null;
    }

    private CompiledRoute getCachedRoute(String path) {
        for (CompiledRoute compiledRoute : cachedRoutes) {
            if (compiledRoute.getRoute().equals(path)) {
                return compiledRoute;
            }
        }

        return null;
    }

    private static Set<String> getNamedGroupCandidates(String regex) {
        Set<String> namedGroups = new TreeSet<>();

        Matcher m = Pattern.compile("\\(\\?<([a-z]+)>").matcher(regex);

        while (m.find()) {
            namedGroups.add(m.group(1));
        }

        return namedGroups;
    }
}
