package org.lyncc.bazinga.rx.bazinga.spi;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Spi {
	
	/**
	 * 单例/多例模式
	 * 
	 * @return
	 */
	Scope scope() default Scope.PROTOTYPE;
	
	public enum Scope{
		SINGLETON,PROTOTYPE
	}

}
