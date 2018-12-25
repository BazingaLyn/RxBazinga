package org.lyncc.bazinga.rx.bazinga.msentinel.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SentinelResource {

    String value() default "";



}
