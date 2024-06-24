package de.sample.schulung.accounts.persistence;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// minimal
@DataJpaTest
@ComponentScan(basePackageClasses = PersistenceLayerTest.class)
// optional
@ActiveProfiles({"test", "persistence-test"})
@Tag("integration-test")
@Tag("persistence-test")
public @interface PersistenceLayerTest {

}
