package de.sample.schulung.accounts.consumer.domain.client;

import lombok.Data;

@Data
public class WebClientConfig {

  private String baseUrl = "http://localhost:8080";
  private int connectionTimeout = 100;
  private long responseTimeout = 1000;

}
