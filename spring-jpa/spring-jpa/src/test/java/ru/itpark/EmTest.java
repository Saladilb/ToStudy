package ru.itpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.entity.Author;
import ru.itpark.reposity.AuthorEmRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmTest {
	@Autowired
	private AuthorEmRepository repository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRepository () {
		{
			Assertions.assertNull(repository.findById(1));
		}

		Author author =
				repository.create(new Author(0, "Vasya"));

		{
			Assertions.assertNotEquals(0, author.getId());
		}

    {
      Assertions.assertNotNull(
          repository.findById(author.getId())
      );
    }

    repository.deleteById(author.getId());

    {
      Assertions.assertNull(repository.findById(1));
    }
	}
}
