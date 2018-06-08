package ru.itpark.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itpark.jackson.demo.Deserializator;
import ru.itpark.jackson.demo.Ignorer;
import ru.itpark.jackson.demo.Referencer;
import ru.itpark.jackson.demo.Serializator;

import java.io.IOException;

@SpringBootApplication
public class JacksonApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JacksonApplication.class, args);
		//Serializator.process();
//		Deserializator.process();
//		Ignorer.process();
		Referencer.process();
	}
}
