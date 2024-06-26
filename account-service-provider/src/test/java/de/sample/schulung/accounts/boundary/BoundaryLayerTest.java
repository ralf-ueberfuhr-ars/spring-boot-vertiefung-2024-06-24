package de.sample.schulung.accounts.boundary;

import de.sample.schulung.accounts.domain.CustomersService;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// minimal
@WebMvcTest
@ComponentScan(basePackageClasses = BoundaryLayerTest.class)
@AutoConfigureMockMvc
@MockBean(CustomersService.class)
// optional
@ActiveProfiles({"test", "boundary-test"})
@Tag("integration-test")
@Tag("boundary-test")
public @interface BoundaryLayerTest {

}
