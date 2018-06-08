package ru.itpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.entity.Author;
import ru.itpark.reposity.AuthorJpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JpaTest {

  @Autowired
  private AuthorJpaRepository repository;

  @Test
  public void testRepository() {
    {
      Assertions.assertEquals(
          0,
          repository.findAll().size()
      );
    }

    {
      repository.save(new Author(0, "Vasya"));
    }

    System.out.println(repository.findAllIds());

    {
      Assertions.assertEquals(
          1,
          repository.findAll().size()
      );
    }

    System.out.println(repository.findAllByNameContainsIgnoreCase("vasya"));
    System.out.println(repository.findAllByNameContainsIgnoreCase("masha"));
    System.out.println(repository.findAllIds("%asya%"));
  }
}
