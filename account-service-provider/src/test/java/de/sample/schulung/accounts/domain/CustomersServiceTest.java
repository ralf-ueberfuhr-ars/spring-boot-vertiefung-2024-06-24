package de.sample.schulung.accounts.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AddressService.class)
@ComponentScan(basePackageClasses = AddressService.class)
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


}
