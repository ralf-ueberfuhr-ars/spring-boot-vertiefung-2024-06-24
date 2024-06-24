package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.domain.sink.CustomersSink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DomainLayerConfiguration.class)
@EnableAutoConfiguration(exclude = {
  DataSourceAutoConfiguration.class,
  DataSourceTransactionManagerAutoConfiguration.class,
  HibernateJpaAutoConfiguration.class
})
@TestPropertySource(
  properties = "application.customers.initialization.enabled=false"
)
public class AddressServiceTests {

  @Autowired
  AddressService sut;
  @MockBean
  CustomersSink sink;

  @AfterEach
  void resetService() {
    sut.reset();
  }

  // @DirtiesContext sorgt für einen neuen Kontext und damit
  // für Injection einer eigenen Service-Instanz
  // -> Umgehen der Performance-Optimierung von Spring Boot
  @Test
  void shouldAddAddress() {
    var address = new Address(
      "Hauptstraße",
      "5A",
      "05896",
      "Musterstadt"
    );
    sut.addAddress(address);
    assertThat(sut.size())
      .isEqualTo(1);
  }

  @Test
  void shouldNotAddAddressTwice() {
    var address = new Address(
      "Hauptstraße",
      "10",
      "05896",
      "Berlin"
    );
    sut.addAddress(address);
    assertThat(sut.size())
      .isEqualTo(1);
    sut.addAddress(address);
    assertThat(sut.size())
      .isEqualTo(1);
  }

}
