package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.domain.sink.CustomersSink;
import de.sample.schulung.accounts.domain.sink.CustomersSinkInMemoryImpl;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

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
@TestPropertySource(
  properties = "application.customers.initialization.enabled=false"
)
// optional
@ActiveProfiles({"test", "domain-test"})
@Tag("integration-test")
@Tag("domain-test")
public @interface DomainLayerTest {

  // TODO enabled-property als value?
  boolean enableInitializer() default false;

  @Configuration
  class DomainLayerConfiguration {

    @Bean
    CustomersSink testCustomerSink() {
      // Mock or DefaultImpl?
      return new CustomersSinkInMemoryImpl();
    }

  }


}
