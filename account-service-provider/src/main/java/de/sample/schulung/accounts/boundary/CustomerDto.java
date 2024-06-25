package de.sample.schulung.accounts.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.sample.schulung.accounts.shared.validation.Adult;
import de.sample.schulung.accounts.shared.validation.CustomerStateString;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID uuid;
  @Size(min = 3, max = 100)
  private String name;
  @JsonProperty("birthdate")
  @Adult
  private LocalDate dateOfBirth;
  @CustomerStateString
  private String state;

}
