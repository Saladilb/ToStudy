package ru.itpark.jackson.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Ignorer {
  public static void process() throws JsonProcessingException {
    User user = new User(1, "password");
    Card visa = new Card(1, "* 4444", user);
    user.getCards().add(visa);

    ObjectMapper mapper = new ObjectMapper();
    String value = mapper
        .writerWithView(View.FullUser.class)
        .writeValueAsString(user);
    System.out.println(value);
  }

  public static class View {
    public interface ShortUser {}
    public interface FullUser extends ShortUser {}
    public interface ShortCard {}
    public interface FullCard extends ShortCard {}
    public interface FullUserWithCards extends FullUser, ShortCard {}
  }

  // альтернатива DTO на классах (Data Transfer Object)

  @JsonView(View.ShortUser.class)
  public static class User {
    private int id;
    @JsonView(View.FullUser.class)
    private String password;
    @JsonView(View.FullUser.class)
    private List<Card> cards = new ArrayList<>();

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

    public List<Card> getCards() {
      return cards;
    }

    public void setCards(List<Card> cards) {
      this.cards = cards;
    }
  }

  @JsonView(View.ShortCard.class)
  public static class Card {
    private int id;
    private String number;
    @JsonView(View.FullCard.class)
    private User owner;

    public Card(int id, String number, User owner) {
      this.id = id;
      this.number = number;
      this.owner = owner;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getNumber() {
      return number;
    }

    public void setNumber(String number) {
      this.number = number;
    }

    public User getOwner() {
      return owner;
    }

    public void setOwner(User owner) {
      this.owner = owner;
    }
  }
}
