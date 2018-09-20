package org.lyncc.bazinga.rx.bazinga.delayqueue.springsupport;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface DelayHandler {

    String topic() default "";

}
