package com.web3horizen.web;

import com.web3horizen.web.router.Matcher;
import com.web3horizen.web.router.Route;
import com.web3horizen.web.servlet.WebApplicationServlet;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebApplication implements Application {
    private final HttpServlet servlet;
    private final List<Route> routes = new ArrayList<>();

    public WebApplication() {
        this.servlet = new WebApplicationServlet(this);
    }

    public HttpServlet getServlet() {
        return servlet;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void get(String path, Class<? extends Controller> controller, String action) {
        routes.add(new Route("GET", path, controller, action));
    }

    public void post(String path, Class<? extends Controller> controller, String action) {
        routes.add(new Route("POST", path, controller, action));
    }

    public void any(String path, Class<? extends Controller> controller, String action) {
        routes.add(new Route("*", path, controller, action));
    }

    public void update(String path, Class<? extends Controller> controller, String action) {
        routes.add(new Route("UPDATE", path, controller, action));
    }

    public void delete(String path, Class<? extends Controller> controller, String action) {
        routes.add(new Route("DELETE", path, controller, action));
    }

    public void handleRequest(Session session, Request request, Response response) {
        Matcher matcher = new Matcher(getRoutes());
        Route route;
        if ((route = matcher.match(request)) != null) {
            try {
                Constructor<?>[] controllerConstructors = route.getController().getConstructors();
                if (controllerConstructors.length == 0) {
                    return;
                }

                Controller controllerInstance = (Controller) controllerConstructors[0].newInstance(session, request, response);
                Method actionMethod = route.getController().getMethod(route.getAction());

                actionMethod.invoke(controllerInstance);

                return;
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        response.write("Not found\n");
    }
}
