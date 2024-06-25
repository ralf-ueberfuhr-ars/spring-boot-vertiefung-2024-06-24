package de.sample.schulung.accounts.config;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface InitializationProperty {

  boolean enableInitializer() default false;

}
