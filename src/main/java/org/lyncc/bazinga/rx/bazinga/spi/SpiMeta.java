package org.lyncc.bazinga.rx.bazinga.spi;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SpiMeta {
	
    String name() default "";
    
}
