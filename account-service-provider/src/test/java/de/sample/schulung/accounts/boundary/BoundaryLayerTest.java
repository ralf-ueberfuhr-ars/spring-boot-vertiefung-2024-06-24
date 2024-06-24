package de.sample.schulung.accounts.boundary;

import de.sample.schulung.accounts.domain.CustomersService;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// minimal
@WebMvcTest(
  properties = {
    "application.customers.initialization.enabled=false"
  }
)
@ComponentScan(basePackageClasses = BoundaryLayerTest.class)
@Import(BoundaryLayerTest.BoundaryLayerTestConfiguration.class)
@AutoConfigureMockMvc
// optional
@ActiveProfiles({"test", "boundary-test"})
@Tag("integration-test")
@Tag("boundary-test")
public @interface BoundaryLayerTest {

  @TestConfiguration
  class BoundaryLayerTestConfiguration {

    @Bean
    CustomersService customersServiceMock() {
      return Mockito.mock(
        CustomersService.class,
        MockReset.withSettings(MockReset.AFTER)
      );
    }

  }

}
