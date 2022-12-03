package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.annotation.*;
import com.web3horizen.hoa.framework.mvc.Controller;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.InternalError;
import com.web3horizen.hoa.framework.mvc.results.NotFound;
import com.web3horizen.hoa.framework.servlet.HttpRequest;
import com.web3horizen.hoa.framework.servlet.HttpResponse;
import com.web3horizen.hoa.framework.servlet.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebApplication extends HttpServlet implements Application {
    private final Class<?> mainModuleClass;
    private final List<Route> routes = new ArrayList<>();

    public WebApplication(Class<?> moduleClass) {
        this.mainModuleClass = moduleClass;
    }

    public Class<?> getMainModuleClass() {
        return mainModuleClass;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public Result handleRequest(Session session, Request request, Response response) {
        Matcher matcher = new Matcher(getRoutes());
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

    public void initModule() {
        processModule(mainModuleClass);
    }

    public void processModule(Class<?> moduleClass) {
        Module moduleAnnotation = moduleClass.getDeclaredAnnotation(Module.class);
        if (moduleAnnotation == null) {
            throw new RuntimeException("Class " + moduleClass + " require @Module annotation!");
        }

        Class<?>[] modules = moduleAnnotation.modules();
        for (Class<?> moduleClass_ : modules) {
            processModule(moduleClass_);
        }

        Class<?>[] controllers = moduleAnnotation.controllers();
        for (Class<?> controllerClass : controllers) {
            processController(controllerClass);
        }
    }

    public void processController(Class<?> controllerClass) {
        com.web3horizen.hoa.framework.annotation.Controller controllerAnnotation = controllerClass.getDeclaredAnnotation(com.web3horizen.hoa.framework.annotation.Controller.class);
        if (controllerAnnotation == null) {
            throw new RuntimeException("Class " + controllerClass.getName() + " require @Controller annotation!");
        }
        processControllerMethods(controllerAnnotation.value(), controllerClass);
    }

    public void processControllerMethods(String controllerPath, Class<?> controllerClass) {
        Method[] methods = controllerClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();

            String requestMethod = "GET";
            String path = null;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Get) {
                    requestMethod = "GET";
                    path = ((Get) annotation).value();
                    break;
                } else if (annotation instanceof Post) {
                    requestMethod = "POST";
                    path = ((Post) annotation).value();
                    break;
                } else if (annotation instanceof Put) {
                    requestMethod = "PUT";
                    path = ((Put) annotation).value();
                    break;
                } else if (annotation instanceof Update) {
                    requestMethod = "UPDATE";
                    path = ((Update) annotation).value();
                    break;
                } else if (annotation instanceof Delete) {
                    requestMethod = "DELETE";
                    path = ((Delete) annotation).value();
                    break;
                }
            }

            if (path == null) {
                continue;
            }

            if (!controllerPath.equals("/")) {
                path = controllerPath + path;
            }

            addRoute(new Route(requestMethod, path, controllerClass, method.getName()));
        }
    }

    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        // create session
        HttpSession session = HttpSession.getSession(servletRequest);

        // create request
        HttpRequest req = new HttpRequest(servletRequest);

        // create response
        HttpResponse res = new HttpResponse(servletResponse);

        initModule();

        Result result = handleRequest(session, req, res);
        result.apply(req, res);
    }
}
