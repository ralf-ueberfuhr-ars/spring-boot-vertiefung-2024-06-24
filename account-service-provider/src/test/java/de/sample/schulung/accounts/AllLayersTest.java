package de.sample.schulung.accounts;

import de.sample.schulung.accounts.config.InitializationProperty;
import de.sample.schulung.accounts.shared.interceptors.AutoConfigureInterceptors;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// minimal
@SpringBootTest
@InitializationProperty
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureInterceptors
// optional
@ActiveProfiles({"test", "all-layers-test"})
@Tag("integration-test")
@Tag("all-layers-test")
public @interface AllLayersTest {

  @AliasFor(annotation = InitializationProperty.class)
  boolean enableInitializer() default false;

}
