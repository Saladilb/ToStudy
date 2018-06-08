package ru.itpark.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable // Address - это не Entity!
public class Address {
  @Column(name = "address_city")
  private String city;
  @Column(name = "address_street")
  private String street;

  public Address() {
  }

  public Address(String city, String street) {
    this.city = city;
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }
}
