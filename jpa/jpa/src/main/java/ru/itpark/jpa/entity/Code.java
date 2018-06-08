package ru.itpark.jpa.entity;

import javax.persistence.Embeddable;

public class Code {
  private String value;

  public Code() {
  }

  public Code(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Code{" +
        "value='" + value + '\'' +
        '}';
  }
}
