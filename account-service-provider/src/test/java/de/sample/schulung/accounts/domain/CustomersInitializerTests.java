package de.sample.schulung.accounts.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@DomainLayerTest(enableInitializer = true)
@TestPropertySource(
  properties = "application.customers.initialization.enabled=true"
)
public class CustomersInitializerTests {

  @MockBean
  CustomersService service;

  @Test
  void shouldBeInitialized() {
    verify(service, atLeastOnce()).createCustomer(any());
  }

}
