package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.annotations.Module;
import com.web3horizen.hoa.framework.annotations.RequestMapping;

import java.lang.reflect.Method;

public class WebApplicationAnnotationProcessor {
    private final Application ctx;

    public WebApplicationAnnotationProcessor(Application ctx) {
        this.ctx = ctx;
    }

    public void process(Class<?> moduleClass) {
        _processModule(moduleClass);
    }

    private void _processModule(Class<?> moduleClass) {
        Module moduleAnnotation = moduleClass.getDeclaredAnnotation(Module.class);
        if (moduleAnnotation == null) {
            throw new RuntimeException("Class " + moduleClass + " require @Module() annotation!");
        }

        Class<?>[] modules = moduleAnnotation.modules();
        for (Class<?> moduleClass_ : modules) {
            _processModule(moduleClass_);
        }

        Class<?>[] controllers = moduleAnnotation.controllers();
        for (Class<?> controllerClass : controllers) {
            _processController(controllerClass);
        }
    }

    private void _processController(Class<?> controllerClass) {
        com.web3horizen.hoa.framework.annotations.Controller controllerAnnotation = controllerClass.getDeclaredAnnotation(com.web3horizen.hoa.framework.annotations.Controller.class);
        if (controllerAnnotation == null) {
            throw new RuntimeException("Class " + controllerClass.getName() + " require @Controller() annotation!");
        }

        _processControllerMethods(controllerAnnotation.value(), controllerClass);
    }

    private void _processControllerMethods(String controllerPath, Class<?> controllerClass) {
        Method[] methods = controllerClass.getDeclaredMethods();
        for (Method method : methods) {
            RequestMapping requestMappingAnnotation = method.getDeclaredAnnotation(RequestMapping.class);
            RequestMethod requestMethod = requestMappingAnnotation.method();
            String path = requestMappingAnnotation.value();

            if (path == null) {
                continue;
            }

            if (!controllerPath.equals("/")) {
                path = controllerPath + path;
            }

            ctx.addRoute(new Route(requestMethod.toString(), path, controllerClass, method.getName()));
        }
    }
}
