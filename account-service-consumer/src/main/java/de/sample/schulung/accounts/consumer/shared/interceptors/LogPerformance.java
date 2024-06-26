package de.sample.schulung.accounts.consumer.shared.interceptors;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogPerformance {
}
