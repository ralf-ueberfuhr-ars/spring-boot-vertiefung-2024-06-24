package de.sample.schulung.accounts.domain.logging;

import de.sample.schulung.accounts.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerEventLogger {

  public void logCustomerCreated(Customer customer) {
    log.info("Customer created with UUID {}", customer.getUuid());
  }

}
