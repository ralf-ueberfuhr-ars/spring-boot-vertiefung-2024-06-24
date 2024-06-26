package de.sample.schulung.accounts.consumer.boundary;

import de.sample.schulung.accounts.consumer.domain.client.CustomerDto;
import de.sample.schulung.accounts.consumer.domain.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/customer-names")
@RequiredArgsConstructor
public class CustomersController {

  private final CustomersService service;

  @GetMapping(
    produces = MediaType.TEXT_PLAIN_VALUE
  )
  @ResponseBody
  public Flux<String> getCustomerNames() {
    return service
      .findAll()
      .map(CustomerDto::getName);
  }



}
