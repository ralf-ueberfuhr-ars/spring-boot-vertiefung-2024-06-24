package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.domain.Customer.CustomerState;
import de.sample.schulung.accounts.domain.events.CustomerCreatedEvent;
import de.sample.schulung.accounts.domain.events.CustomerDeletedEvent;
import de.sample.schulung.accounts.domain.events.CustomerReplacedEvent;
import de.sample.schulung.accounts.domain.sink.CustomersSink;
import de.sample.schulung.accounts.shared.interceptors.LogPerformance;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
public class CustomersService {

  private final CustomersSink sink;
  private final ApplicationEventPublisher eventPublisher;

  public Stream<Customer> getCustomers() {
    return sink.getCustomers();
  }

  public Stream<Customer> getCustomersByState(@NotNull CustomerState state) {
    return sink.getCustomersByState(state);
  }

  @LogPerformance
  public void createCustomer(@Valid Customer customer) {
    sink.createCustomer(customer);
    eventPublisher.publishEvent(new CustomerCreatedEvent(customer));
  }

  public Optional<Customer> findCustomerById(@NotNull UUID uuid) {
    return sink.findCustomerById(uuid);
  }

  @LogPerformance
  public void replaceCustomer(@Valid Customer customer) {
      sink.replaceCustomer(customer);
      eventPublisher.publishEvent(new CustomerReplacedEvent(customer));
  }

  @LogPerformance
  public void deleteCustomer(@NotNull UUID uuid) {
    sink.deleteCustomer(uuid);
    eventPublisher.publishEvent(new CustomerDeletedEvent(uuid));
  }

  public boolean exists(UUID uuid) {
    return sink.exists(uuid);
  }

  public long count() {
    return sink.count();
  }

}
