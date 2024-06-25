package de.sample.schulung.accounts.config;

import lombok.experimental.UtilityClass;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.util.Optional;

@UtilityClass
class TestAnnotationUtil {

  public <T extends Annotation> Optional<T> findMergedAnnotation(Class<?> testClass, Class<T> annotationType) {
    if (null == testClass) {
      return Optional.empty();
    }
    return Optional
      .ofNullable(AnnotatedElementUtils.findMergedAnnotation(testClass, annotationType))
      // search outer class (@Nested class)
      .or(() -> findMergedAnnotation(testClass.getDeclaringClass(), annotationType));
  }


}
