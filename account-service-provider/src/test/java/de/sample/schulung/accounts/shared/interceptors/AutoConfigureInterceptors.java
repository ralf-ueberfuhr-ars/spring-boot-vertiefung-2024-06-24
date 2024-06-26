package de.sample.schulung.accounts.shared.interceptors;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@ComponentScan(basePackageClasses = AutoConfigureInterceptors.class)
public @interface AutoConfigureInterceptors {
}
