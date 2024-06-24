package de.sample.schulung.accounts.domain;

public record Address(
  String street,
  String nr,
  String zip,
  String city
) {
}
