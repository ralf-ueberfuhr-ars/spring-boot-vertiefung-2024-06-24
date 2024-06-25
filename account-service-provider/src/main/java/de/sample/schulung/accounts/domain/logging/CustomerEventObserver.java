package de.sample.schulung.accounts.domain.logging;

import de.sample.schulung.accounts.domain.events.CustomerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerEventObserver {

  private final CustomerEventLogger customerEventLogger;

  @EventListener
  public void handle(CustomerCreatedEvent event) {
    customerEventLogger.logCustomerCreated(event.customer());
  }

}
