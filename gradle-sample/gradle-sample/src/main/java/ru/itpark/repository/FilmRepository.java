package ru.itpark.repository;

import ru.itpark.domain.Film;

import java.util.List;

public interface FilmRepository {
    int create(String title, String description);
    void create(List<Film> films);
    Film findById(int id);
    void deleteById(int id);
}
