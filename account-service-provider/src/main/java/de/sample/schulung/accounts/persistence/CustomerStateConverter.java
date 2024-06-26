package de.sample.schulung.accounts.persistence;

import de.sample.schulung.accounts.domain.Customer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.PersistenceException;

@Converter(autoApply = true)
public class CustomerStateConverter
  implements AttributeConverter<Customer.CustomerState, String> {

  @Override
  public String convertToDatabaseColumn(Customer.CustomerState source) {
    return null == source ? null : switch (source) {
      case ACTIVE -> "a";
      case DISABLED -> "d";
      case LOCKED -> "l";
    };
  }

  @Override
  public Customer.CustomerState convertToEntityAttribute(String source) {
    return null == source ? null : switch (source) {
      case "active", "a" -> Customer.CustomerState.ACTIVE;
      case "d" -> Customer.CustomerState.DISABLED;
      case "l" -> Customer.CustomerState.LOCKED;
      default -> throw new PersistenceException();
    };
  }
}
