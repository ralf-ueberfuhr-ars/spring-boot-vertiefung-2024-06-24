package de.sample.schulung.accounts.boundary;

import de.sample.schulung.accounts.domain.CustomersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@BoundaryLayerTest
public class IndexPageTests {

  @Autowired
  MockMvc mvc;
  @Autowired
  CustomersService service;

  @Test
  void shouldRedirectIndexPage() throws Exception {
    var location = mvc.perform(
        get("/")
      )
      .andExpect(status().isFound())
      .andExpect(header().exists("Location"))
      .andReturn()
      .getResponse()
      .getHeader("Location");
    assertThat(location).endsWith("/index.html");
  }

}
