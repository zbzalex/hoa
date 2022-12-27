package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.annotation.Module;
import com.web3horizen.hoa.framework.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WebApplicationAnnotationRuntimeProcessor {
    public List<Route> processModule(Class<?> moduleClass) {
        List<Route> routes = new ArrayList<>();

        Module moduleAnnotation = moduleClass.getDeclaredAnnotation(Module.class);
        if (moduleAnnotation == null) {
            throw new RuntimeException("Class " + moduleClass + " require @Module() annotation!");
        }

        Class<?>[] modules = moduleAnnotation.modules();
        for (Class<?> moduleClass_ : modules) {
            routes.addAll(processModule(moduleClass_));
        }

        Class<?>[] controllers = moduleAnnotation.controllers();
        for (Class<?> controllerClass : controllers) {
            routes.addAll(processController(controllerClass));
        }

        return routes;
    }

    public List<Route> processController(Class<?> controllerClass) {
        com.web3horizen.hoa.framework.annotation.Controller controllerAnnotation = controllerClass.getDeclaredAnnotation(com.web3horizen.hoa.framework.annotation.Controller.class);
        if (controllerAnnotation == null) {
            throw new RuntimeException("Class " + controllerClass.getName() + " require @Controller() annotation!");
        }

        String controllerPath = controllerAnnotation.value();

        return processMethods(controllerPath, controllerClass);
    }

    public List<Route> processMethods(String controllerPath, Class<?> controllerClass) {
        List<Route> routes = new ArrayList<>();
        Method[] methods = controllerClass.getDeclaredMethods();
        for (Method method : methods) {
            RequestMapping requestMappingAnnotation = method.getDeclaredAnnotation(RequestMapping.class);
            if (requestMappingAnnotation == null) {
                continue;
            }

            RequestMethod requestMethod = requestMappingAnnotation.method();
            String path = requestMappingAnnotation.value();

            if (path == null) {
                continue;
            }

            routes.add(compileRoute(controllerPath, requestMethod, path, controllerClass, method.getName()));
        }

        return routes;
    }

    private Route compileRoute(String controllerPath, RequestMethod requestMethod, String path, Class<?> controllerClass, String method) {
        if (controllerPath != null && !controllerPath.equals("/")) {
            path = controllerPath + path;
        }

        return new Route(requestMethod.toString(), path, controllerClass, method);
    }
}
