package ru.itpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itpark.domain.Wish;
import ru.itpark.repository.WishRepository;

@SpringBootApplication
public class WishlistApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WishlistApplication.class, args);
		WishRepository wishRepository = context.getBean(WishRepository.class);
		wishRepository.save(new Wish(1, "First", "Content", 0));
		wishRepository.save(new Wish(2, "Second", "Content", 0));
	}
}
