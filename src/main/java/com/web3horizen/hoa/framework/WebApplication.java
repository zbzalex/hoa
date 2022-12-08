package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.mvc.Controller;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.InternalError;
import com.web3horizen.hoa.framework.mvc.results.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebApplication extends HttpServlet implements Application {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<Route> routes = new ArrayList<>();
    private final WebApplicationAnnotationProcessor annotationProcessor = new WebApplicationAnnotationProcessor(this);
    private boolean _initialized = false;

    public WebApplication() {
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public Result handleRequest(Session session, Request request, Response response) {
        Matcher matcher = new Matcher(routes);
        Route route;
        if ((route = matcher.match(request)) != null) {
            try {
                Constructor<?>[] controllerConstructors = route.getController().getConstructors();
                if (controllerConstructors.length == 0) {
                    return new InternalError();
                }

                Controller controllerInstance = (Controller) controllerConstructors[0].newInstance(session, request, response);
                Method actionMethod = route.getController().getMethod(route.getAction());

                return (Result) actionMethod.invoke(controllerInstance);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return new InternalError();
            }
        }

        return new NotFound();
    }

    public void initModule(Class<?> moduleClass) {
        logger.debug("initModule()");

        if (_initialized) {
            throw new RuntimeException("Module already initialized!");
        }

        logger.debug("module initialization..");

        _initialized = true;
        annotationProcessor.process(moduleClass);
    }

    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        HttpSession session = HttpSession.getSession(servletRequest);
        HttpRequest req = new HttpRequest(servletRequest);

        HttpResponse res = new HttpResponse(servletResponse);

        Result result = handleRequest(session, req, res);

        result.apply(req, res);

        terminate();
    }

    protected void terminate() {

    }
}
