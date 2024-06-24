package de.sample.schulung.accounts.domain;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AddressService {

  private final Set<Address> addresses = new HashSet<>();

  public void addAddress(Address address) {
    this.addresses.add(address);
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public long size() {
    return addresses.size();
  }

  void reset() {
    addresses.clear();
  }

}
