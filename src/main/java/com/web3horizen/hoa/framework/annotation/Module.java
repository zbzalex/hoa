package com.web3horizen.hoa.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
    Class<?>[] modules() default {};
    Class<?>[] controllers() default {};
}
