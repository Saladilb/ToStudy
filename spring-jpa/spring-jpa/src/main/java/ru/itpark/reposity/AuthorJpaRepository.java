package ru.itpark.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itpark.entity.Author;

import java.util.List;

@Repository
public interface AuthorJpaRepository
  extends JpaRepository<Author, Integer>
{
  @Query("select a.id from Author a")
  List<Integer> findAllIds();


  @Query("select a.id from Author a where a.name like :name")
  List<Integer> findAllIds(@Param("name") String name);

  List<Author> findAllByNameContainsIgnoreCase(String name);
}
