package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.mvc.Controller;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.InternalError;
import com.web3horizen.hoa.framework.mvc.results.NotFound;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class RequestHandler {
    protected final List<Route> routes;

    public RequestHandler(List<Route> routes) {
        this.routes = routes;
    }

    Result handle(Request request, Response response) {
        RouteMatcher matcher = new RouteMatcher(routes);

        CompiledRoute compiledRoute;
        if ((compiledRoute = matcher.match(request)) != null) {
            request.setAttributes(compiledRoute.getParams());
            try {
                Constructor<?>[] controllerConstructors = compiledRoute.getRoute().getController().getConstructors();
                if (controllerConstructors.length == 0) {
                    return new InternalError();
                }

                Controller controllerInstance = (Controller) controllerConstructors[0].newInstance(request, response);
                Method actionMethod = compiledRoute.getRoute().getController().getMethod(
                        compiledRoute.getRoute().getAction()
                );

                return (Result) actionMethod.invoke(controllerInstance);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return new InternalError();
            }
        }

        return new NotFound();
    }
}
