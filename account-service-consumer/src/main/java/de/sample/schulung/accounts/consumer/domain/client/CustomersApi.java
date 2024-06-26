package de.sample.schulung.accounts.consumer.domain.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;

@HttpExchange("/customers")
public interface CustomersApi {

  @GetExchange
  Flux<CustomerDto> readAllCustomers();

}
