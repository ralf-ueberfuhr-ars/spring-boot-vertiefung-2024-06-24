package de.sample.schulung.accounts.consumer.domain.client;

import io.netty.channel.ChannelOption;
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
  CustomersApi customersApi() {
    var httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100)
      .responseTimeout(Duration.ofMillis(1000));
    var webClient = WebClient
      .builder()
      .baseUrl("http://localhost:8080/api/v1")
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();
    var adapter = WebClientAdapter
      .create(webClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory
      .builderFor(adapter)
      .build();

    return factory
      .createClient(CustomersApi.class);
  }

}
