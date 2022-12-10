package com.web3horizen.hoa.framework.annotation.processor;

import com.web3horizen.hoa.framework.AnnotationProcessor;
import com.web3horizen.hoa.framework.Application;

public class ControllerAnnotationProcessor implements AnnotationProcessor {
    private final Application context;
    private final ControllerMethodAnnotationProcessor controllerMethodAnnotationProcessor;

    public ControllerAnnotationProcessor(Application context) {
        this.context = context;
        this.controllerMethodAnnotationProcessor = new ControllerMethodAnnotationProcessor(context);
    }

    public void process(Class<?> controllerClass) {
        com.web3horizen.hoa.framework.annotation.Controller controllerAnnotation = controllerClass.getDeclaredAnnotation(com.web3horizen.hoa.framework.annotation.Controller.class);
        if (controllerAnnotation == null) {
            throw new RuntimeException("Class " + controllerClass.getName() + " require @Controller() annotation!");
        }

        controllerMethodAnnotationProcessor.process(controllerAnnotation.value(), controllerClass);
    }
}
