package ru.itpark.service;

import ru.itpark.domain.Wish;

import java.util.List;

public interface WishService {
  List<Wish> findAll();

  void save(Wish wish);

  void delete(int id);

  Wish findById(int id);

  void setLike(int id);

  void setDislike(int id);

  Wish getMaxLikes();

  void addWish(Wish wish);
}
