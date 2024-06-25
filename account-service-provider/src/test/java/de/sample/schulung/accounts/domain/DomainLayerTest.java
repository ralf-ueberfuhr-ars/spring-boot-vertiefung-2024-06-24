package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.config.InitializationProperty;
import de.sample.schulung.accounts.domain.logging.CustomerEventLogger;
import de.sample.schulung.accounts.domain.sink.CustomersSink;
import de.sample.schulung.accounts.domain.sink.CustomersSinkInMemoryImpl;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// minimal
@SpringBootTest(classes = DomainLayerTest.DomainLayerConfiguration.class)
@ComponentScan(basePackageClasses = de.sample.schulung.accounts.domain.DomainLayerTest.class)
@EnableAutoConfiguration(exclude = {
  DataSourceAutoConfiguration.class,
  DataSourceTransactionManagerAutoConfiguration.class,
  HibernateJpaAutoConfiguration.class
})
@InitializationProperty
// optional
@ActiveProfiles({"test", "domain-test"})
@Tag("integration-test")
@Tag("domain-test")
public @interface DomainLayerTest {

  @AliasFor(annotation = InitializationProperty.class)
  boolean enableInitializer() default false;

  @Configuration
  class DomainLayerConfiguration {

    @Primary
    @Bean
    CustomersSink testCustomerSink() {
      // Mock or DefaultImpl?
      return new CustomersSinkInMemoryImpl();
    }

    @Primary
    @Bean
    CustomerEventLogger testCustomerEventLogger() {
      // Mock or DefaultImpl?
      return Mockito.mock(
        CustomerEventLogger.class,
        MockReset.withSettings(MockReset.AFTER)
      );
    }

  }


}
