package com.web3horizen.hoa.framework.annotation.processor;

import com.web3horizen.hoa.framework.AnnotationProcessor;
import com.web3horizen.hoa.framework.Application;
import com.web3horizen.hoa.framework.RequestMethod;
import com.web3horizen.hoa.framework.Route;
import com.web3horizen.hoa.framework.annotation.RequestMapping;

import java.lang.reflect.Method;

public class ControllerMethodAnnotationProcessor implements AnnotationProcessor {
    private final Application context;
    private String controllerPath;

    public ControllerMethodAnnotationProcessor(Application context) {
        this.context = context;
    }

    public void process(String controllerPath, Class<?> controllerClass) {
        this.controllerPath = controllerPath;
        process(controllerClass);
    }

    @Override
    public void process(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            RequestMapping requestMappingAnnotation = method.getDeclaredAnnotation(RequestMapping.class);
            RequestMethod requestMethod = requestMappingAnnotation.method();
            String path = requestMappingAnnotation.value();

            if (path == null) {
                continue;
            }

            putRoute(requestMethod, path, clazz, method.getName());
        }
    }

    private void putRoute(RequestMethod requestMethod, String path, Class<?> controllerClass, String method) {
        if (controllerPath != null && !controllerPath.equals("/")) {
            path = controllerPath + path;
        }

        context.getRoutes().add(
                new Route(requestMethod.toString(), path, controllerClass, method)
        );
    }
}
