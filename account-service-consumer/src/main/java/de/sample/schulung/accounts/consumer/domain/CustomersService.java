package de.sample.schulung.accounts.consumer.domain;

import de.sample.schulung.accounts.consumer.domain.client.CustomerDto;
import de.sample.schulung.accounts.consumer.domain.client.CustomersApi;
import de.sample.schulung.accounts.consumer.shared.interceptors.LogPerformance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CustomersService {

  private final CustomersApi remoteApi;

  @LogPerformance
  public Flux<CustomerDto> findAll() {
    return remoteApi
      .readAllCustomers();
  }

}
