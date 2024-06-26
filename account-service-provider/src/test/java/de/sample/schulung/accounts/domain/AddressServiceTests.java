package de.sample.schulung.accounts.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DomainLayerTest
public class AddressServiceTests {

  @Autowired
  AddressService sut;

  //@AfterEach
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
   //assertThat(sut.size())
   //   .isEqualTo(1);
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
    //assertThat(sut.size())
    //  .isEqualTo(1);
    sut.addAddress(address);
    //assertThat(sut.size())
    //  .isEqualTo(1);
  }

}
