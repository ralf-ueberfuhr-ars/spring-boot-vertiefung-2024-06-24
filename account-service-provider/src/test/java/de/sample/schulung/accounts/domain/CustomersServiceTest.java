package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.domain.sink.CustomersSink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = DomainLayerConfiguration.class)
@EnableAutoConfiguration(exclude = {
  DataSourceAutoConfiguration.class,
  DataSourceTransactionManagerAutoConfiguration.class,
  HibernateJpaAutoConfiguration.class
})
@TestPropertySource(
  properties = "application.customers.initialization.enabled=false"
)
public class CustomersServiceTest {

  @Autowired
  CustomersService service;
  @MockBean
  CustomersSink sink;

  @Test
  void shouldNotCreateInvalidCustomer() {
    var customer = new Customer();
    customer.setName("T");
    customer.setState(Customer.CustomerState.ACTIVE);
    customer.setDateOfBirth(LocalDate.of(2000, Month.DECEMBER, 20));

    assertThatThrownBy(() -> service.createCustomer(customer))
      .isNotNull();

  }


}
