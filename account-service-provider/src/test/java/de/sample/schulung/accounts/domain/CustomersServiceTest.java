package de.sample.schulung.accounts.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DomainLayerTest
public class CustomersServiceTest {

  @Autowired
  CustomersService service;

  @Test
  void shouldNotCreateInvalidCustomer() {
    var customer = new Customer();
    customer.setName("T");
    customer.setState(Customer.CustomerState.ACTIVE);
    customer.setDateOfBirth(LocalDate.of(2000, Month.DECEMBER, 20));

    assertThatThrownBy(() -> service.createCustomer(customer))
      .isNotNull();

  }

  @Test
  void shouldNotCreateCustomerThatIsNoAdult() {
    var customer = new Customer();
    customer.setName("Tom Mayer");
    customer.setState(Customer.CustomerState.ACTIVE);
    customer.setDateOfBirth(LocalDate.now().minusYears(17));

    assertThatThrownBy(() -> service.createCustomer(customer))
      .isNotNull();

  }


}
