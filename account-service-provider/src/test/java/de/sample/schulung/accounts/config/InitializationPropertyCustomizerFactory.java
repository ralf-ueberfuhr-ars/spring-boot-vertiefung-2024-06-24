package de.sample.schulung.accounts.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;
import org.springframework.test.context.MergedContextConfiguration;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public class InitializationPropertyCustomizerFactory implements ContextCustomizerFactory {

  @Override
  public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
    return Optional
      .ofNullable(AnnotatedElementUtils.findMergedAnnotation(testClass, InitializationProperty.class))
      .map(InitializationProperty::enableInitializer)
      //.map(this::createContextCustomizer)
      .map(InitializationContextCustomizer::new)
      .orElse(null);
  }

  /*
   * This class must implement equals()/hashcode() because it is used as a key for the Spring Boot Context Cache!
   * (Records typically do so.)
   */
  private record InitializationContextCustomizer(boolean enabled) implements ContextCustomizer {
    @Override
    public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
      TestPropertyValues
        .of("application.customers.initialization.enabled=" + enabled)
        .applyTo(context);
    }
  }

}
