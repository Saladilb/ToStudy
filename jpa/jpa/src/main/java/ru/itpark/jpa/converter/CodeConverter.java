package ru.itpark.jpa.converter;

import org.springframework.stereotype.Component;
import ru.itpark.jpa.entity.Code;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;

@Converter(autoApply = true)
public class CodeConverter implements AttributeConverter<Code, String> {

  @Override
  public String convertToDatabaseColumn(Code attribute) {
    return Base64.getEncoder().encodeToString(attribute.getValue().getBytes());
  }

  @Override
  public Code convertToEntityAttribute(String dbData) {
    Code result = new Code();
    String code = new String(Base64.getDecoder().decode(dbData));
    result.setValue(code);
    return result;
  }
}
