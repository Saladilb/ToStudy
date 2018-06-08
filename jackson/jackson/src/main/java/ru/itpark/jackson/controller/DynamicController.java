package ru.itpark.jackson.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.jackson.demo.Ignorer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dynamic")
public class DynamicController {
  // ResponseEntity
  @GetMapping
  public MappingJacksonValue getUser() {
    User user = new User(1, "password");

    // Какие поля нужно сериализовать, а какие нет
    FilterProvider provider = new SimpleFilterProvider()
        .addFilter(
            "userFilter",
            SimpleBeanPropertyFilter.serializeAllExcept(
                "id"
            )
        );
    MappingJacksonValue value = new MappingJacksonValue(user);
    value.setFilters(provider);
    return value;
  }

  @JsonFilter("userFilter")
  public static class User {
    private int id;
    private String password;

    public User() {
    }

    public User(int id, String password) {
      this.id = id;
      this.password = password;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
