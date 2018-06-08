package ru.itpark.jackson.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.jackson.demo.Ignorer;

@RestController
@RequestMapping("/main")
public class MainController {
  @JsonView(Ignorer.View.ShortUser.class)
  @GetMapping
  public Ignorer.User getUser() {
    Ignorer.User user = new Ignorer.User(1, "password");
    Ignorer.Card visa = new Ignorer.Card(1, "* 4444", user);
    user.getCards().add(visa);
    return user;
  }

  @JsonView(Ignorer.View.FullUserWithCards.class)
  @GetMapping("/full")
  public Ignorer.User getUserFull() {
    Ignorer.User user = new Ignorer.User(1, "password");
    Ignorer.Card visa = new Ignorer.Card(1, "* 4444", user);
    user.getCards().add(visa);
    return user;
  }
}
