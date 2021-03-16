package com.company.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//希望该注解可以使用在类上
@Retention(RetentionPolicy.RUNTIME)//希望注解被保留到RUNTIME阶段
public @interface Pro {
    String className();
    String methoName();
}
