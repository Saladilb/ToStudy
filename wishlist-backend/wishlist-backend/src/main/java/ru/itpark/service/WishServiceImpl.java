package ru.itpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.domain.Wish;
import ru.itpark.repository.WishRepository;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {
    @Autowired
    private WishRepository repository;

    @Override
    public List<Wish> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Wish wish) {
        repository.save(wish);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Wish findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void setLike(int id) {
        repository.setLike(id);
    }

    @Override
    public void setDislike(int id) {
        repository.setDislike(id);
    }

    @Override
    public Wish getMaxLikes() {
        return repository.getMaxLikes();
    }

    @Override
    public void addWish(Wish wish) {
        repository.save(wish);
    }
}
