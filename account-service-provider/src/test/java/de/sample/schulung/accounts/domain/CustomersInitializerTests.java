package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.config.EnableBeanValidation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = EnableBeanValidation.class)
@ComponentScan(basePackageClasses = CustomersService.class)
@EnableAutoConfiguration(exclude = {
  DataSourceAutoConfiguration.class,
  DataSourceTransactionManagerAutoConfiguration.class,
  HibernateJpaAutoConfiguration.class
})
@TestPropertySource(
  properties = "application.customers.initialization.enabled=false"
)
@Disabled
public class CustomersInitializerTests {

  @MockBean
  CustomersService service;

  @Test
  void shouldBeInitialized() {
    verify(service, atLeastOnce()).createCustomer(any());
  }

}
