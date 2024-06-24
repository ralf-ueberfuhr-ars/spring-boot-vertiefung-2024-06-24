package de.sample.schulung.accounts.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.List;
import java.util.Optional;

public class InitializationPropertyCustomizerFactory implements ContextCustomizerFactory {

  @SuppressWarnings("NullableProblems")
  @Override
  public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
    return Optional
      .ofNullable(AnnotatedElementUtils.findMergedAnnotation(testClass, InitializationProperty.class))
      .map(InitializationProperty::enableInitializer)
      .map(this::createContextCustomizer)
      .orElse(null);
  }

  private ContextCustomizer createContextCustomizer(final boolean enabled) {
    return (context, __) -> TestPropertyValues
      .of("application.customers.initialization.enabled=" + enabled)
      .applyTo(context);
  }

}
