package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.annotation.processor.ModuleAnnotationProcessor;
import com.web3horizen.hoa.framework.mvc.Controller;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.InternalError;
import com.web3horizen.hoa.framework.mvc.results.NotFound;
import com.web3horizen.hoa.framework.server.JettyHttpServer;
import com.web3horizen.hoa.framework.servlet.WebApplicationServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebApplication implements Application {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private HttpServer server = new JettyHttpServer(18080);
    private Class<?> mainModuleClass;
    private final List<Route> routes = new ArrayList<>();
    private final ModuleAnnotationProcessor moduleAnnotationProcessor = new ModuleAnnotationProcessor(this);

    public WebApplication() {
    }

    public WebApplication(HttpServer server) {
        this.server = server;
    }


    public void setMainModuleClass(Class<?> mainModuleClass) {
        this.mainModuleClass = mainModuleClass;
    }

    public List<Route> getRoutes() {
        return routes;
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

    public void run() {
        moduleAnnotationProcessor.process(mainModuleClass);

        server.start(new WebApplicationServlet(this));

        terminate();
    }

    public void terminate() {

    }
}
