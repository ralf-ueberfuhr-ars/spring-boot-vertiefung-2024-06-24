package de.sample.schulung.accounts.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CustomersServiceTest.class)
public class DomainLayerConfiguration {
}
