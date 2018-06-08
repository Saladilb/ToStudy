package ru.itpark.jackson.demo;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Serializator {
  public static void process() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.enable(SerializationFeature.WRAP_ROOT_VALUE); // без включения работать @JsonRootName работать не будет 
    String value = mapper.writeValueAsString(
        new Credit(1006, 1, "Всё будет хорошо")
    );
    System.out.println(value);
  }

  // если нет property, то Exception in thread "main" com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class ru.itpark.jackson.demo.Serializator$Credit and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
  @JsonPropertyOrder("id")
  @JsonRootName("data")
  public static class Credit {
    private int account;
    private int id;
    private String name;
    private Date opened;

    public Credit(int account, int id, String name) {
      this.account = account;
      this.id = id;
      this.name = name;
      opened = new Date();
    }

    // если нет getter'ов то получаем пустой объект
    public int getAccount() {
      return account;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    @JsonGetter("code")
    public String coded() {
      return String.valueOf(id) + ":" + name;
    }

    @JsonAnyGetter
    public Map<String, String> features() {
      return Map.of(
          "limit", "100 000 рублей",
          "percent", "самый выгодный"
      );
    }

    @JsonRawValue
    public String raw() {
      return "[\"raw\"]";
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOpened() {
      return opened;
    }
  }

  static class CustomDateSerializer extends StdSerializer<Date> {
    public CustomDateSerializer() {
      super(Date.class);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
      gen.writeString(format.format(value));
    }
  }
}
