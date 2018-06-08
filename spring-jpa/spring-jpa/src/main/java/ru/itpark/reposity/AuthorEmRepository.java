package ru.itpark.reposity;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.entity.Author;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class AuthorEmRepository {
  private final EntityManager entityManager;

  public AuthorEmRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Author> findAll() {
    return entityManager.createNamedQuery(
        "Author.findAll", Author.class
    ).getResultList();
  }

  public Author findById(int id) {
    return entityManager.find(Author.class, id);
  }

  public Author create(Author author) {
    return entityManager.merge(author);
  }

  public Author update(Author author) {
    return entityManager.merge(author);
  }

  public void deleteById(int id) {
//    Author author = findById(id);
//    if (author != null) {
//      entityManager.remove(author);
//    }
    entityManager
        .createQuery(
            "delete from Author a " +
            "where a.id = :id"
        )
        .setParameter("id", id)
        .executeUpdate();
  }
}
