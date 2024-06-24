package de.sample.schulung.accounts.domain.sink;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class CustomerSinkAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  CustomersSink inMemoryCustomersSink() {
    return new CustomersSinkInMemoryImpl();
  }

}
