package de.sample.schulung.accounts.consumer.domain.client;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class CustomersApiConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "application.api-clients.customers")
  WebClientConfig customersWebClientConfig() {
    return new WebClientConfig();
  }

  @Bean
  WebClient customersWebClient(
    @Qualifier("customersWebClientConfig")
    WebClientConfig config
  ) {
    var httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getConnectionTimeout())
      .responseTimeout(Duration.ofMillis(config.getResponseTimeout()));
    return WebClient
      .builder()
      .baseUrl(config.getBaseUrl())
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();
  }

  @Bean
  CustomersApi customersApi(
    @Qualifier("customersWebClient")
    WebClient customersWebClient
  ) {
    var adapter = WebClientAdapter
      .create(customersWebClient);
    return HttpServiceProxyFactory
      .builderFor(adapter)
      .build()
      .createClient(CustomersApi.class);
  }

}
