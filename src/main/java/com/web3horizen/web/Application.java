package com.web3horizen.web;

import com.web3horizen.web.router.Route;

import javax.servlet.http.HttpServlet;
import java.util.List;

public interface Application {
    List<Route> getRoutes();

    void addRoute(Route route);

    void get(String path, Class<? extends Controller> controller, String action);

    void post(String path, Class<? extends Controller> controller, String action);

    void any(String path, Class<? extends Controller> controller, String action);

    void update(String path, Class<? extends Controller> controller, String action);

    void delete(String path, Class<? extends Controller> controller, String action);

    void handleRequest(Session session, Request request, Response response);

    HttpServlet getServlet();
}
