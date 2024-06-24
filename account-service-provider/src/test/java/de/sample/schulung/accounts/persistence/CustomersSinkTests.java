package de.sample.schulung.accounts.persistence;

import de.sample.schulung.accounts.domain.Customer;
import de.sample.schulung.accounts.domain.sink.CustomersSink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@PersistenceLayerTest
public class CustomersSinkTests {

  @Autowired
  CustomersSink sink;

  @Test
  void shouldBeCorrectImplementation() {
    assertThat(sink)
      .isInstanceOf(CustomersSinkJpaImpl.class);
  }

  @Test
  void shouldSaveCustomer() {
    var customer = new Customer(
      null,
      "Max",
      LocalDate.of(2010, Month.FEBRUARY, 10),
      Customer.CustomerState.ACTIVE
    );
    sink.createCustomer(customer);
    assertThat(customer)
      .extracting(Customer::getUuid)
      .isNotNull();
    assertThat(sink.findCustomerById(customer.getUuid()))
      .isPresent()
      .get()
      .usingRecursiveComparison()
      .isEqualTo(customer);
  }

}
