package ru.itpark.jackson.demo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Deserializator {
  public static void process() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    // Alt + Enter -> Inject Language Reference - > JSON
    // Alt + Enter -> Edit JSON Fragment
    String json = "{\n" +
        "  \"id\": 1,\n" +
        "  \"title\": \"Всё хорошо\",\n" +
        "  \"description\": \"Описание\",\n" +
        "  \"tag\": \"#teens\",\n" +
        "  \"account\": \"1000005234\",\n" +
        "  \"opened\": \"18.03.2018\"\n" +
        "}";
    Object value = mapper.readerFor(Credit.class)
        .readValue(json);
    System.out.println(value);

  }

  public static class Credit {
    private int id;
    private String name;
    private Map<String, Object> features = new HashMap<>();
    private Date opened;

    public Credit() {
    }

    public void setId(int id) {
      this.id = id;
    }

    @JsonAlias("title")
    public void setName(String name) {
      this.name = name;
    }

    @JsonAnySetter
    public void values(String name, Object value) {
      System.out.println(name);
      features.put(name, value);
    }

    @JsonSetter("account")
    public void account(Object value) {
      System.out.println("from setter:" + value);
    }


    @JsonDeserialize(using = CustomDateDeserializer.class)
    public void setOpened(Date opened) {
      this.opened = opened;
    }


    @Override
    public String toString() {
      return "Credit{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", features=" + features +
          ", opened=" + opened +
          '}';
    }
  }

  private static class CustomDateDeserializer extends StdDeserializer<Date> {
    public CustomDateDeserializer() {
      super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      String date = p.getText();
      SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


      try {
        return format.parse(date);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
