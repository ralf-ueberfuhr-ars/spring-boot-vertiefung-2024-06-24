package de.sample.schulung.accounts;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// minimal
@SpringBootTest
@TestPropertySource(
  properties = "application.customers.initialization.enabled=false"
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
// optional
@ActiveProfiles({"test", "all-layers-test"})
@Tag("integration-test")
@Tag("all-layers-test")
public @interface AllLayersTest {

  // TODO enabled-property als value?
  boolean enableInitializer() default false;

}
