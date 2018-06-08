package ru.itpark.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itpark.jpa.entity.Address;
import ru.itpark.jpa.entity.Lead;
import ru.itpark.jpa.repository.LeadRepository;
import ru.itpark.jpa.service.LeadService;

import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(JpaApplication.class, args);

		context.getBean(LeadService.class).process();
	}
}
