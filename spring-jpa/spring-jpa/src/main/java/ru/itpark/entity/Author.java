package ru.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NamedQueries(
    @NamedQuery(
        name = "Author.findAll",
        query = "select a from Author a"
    )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  @Id
  @GeneratedValue
  private int id;

  private String name;
}
