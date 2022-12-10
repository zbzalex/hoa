package com.web3horizen.hoa.framework.annotation.processor;

import com.web3horizen.hoa.framework.AnnotationProcessor;
import com.web3horizen.hoa.framework.Application;
import com.web3horizen.hoa.framework.annotation.Module;

public class ModuleAnnotationProcessor implements AnnotationProcessor {
    private final Application context;
    private final ControllerAnnotationProcessor controllerAnnotationProcessor;

    public ModuleAnnotationProcessor(Application context) {
        this.context = context;
        this.controllerAnnotationProcessor = new ControllerAnnotationProcessor(context);
    }

    public void process(Class<?> moduleClass) {
        Module moduleAnnotation = moduleClass.getDeclaredAnnotation(Module.class);
        if (moduleAnnotation == null) {
            throw new RuntimeException("Class " + moduleClass + " require @Module() annotation!");
        }

        Class<?>[] modules = moduleAnnotation.modules();
        for (Class<?> moduleClass_ : modules) {
            process(moduleClass_);
        }

        Class<?>[] controllers = moduleAnnotation.controllers();
        for (Class<?> controllerClass : controllers) {
            controllerAnnotationProcessor.process(controllerClass);
        }
    }
}
