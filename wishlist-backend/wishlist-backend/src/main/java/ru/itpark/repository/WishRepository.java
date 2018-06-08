package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.domain.Wish;

@Repository
public interface WishRepository extends JpaRepository<Wish, Integer> {
    @Modifying
    @Transactional
    @Query("update Wish w set w.likes=w.likes+1 where id=:id")
    void setLike(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("update Wish w set w.likes=w.likes-1 where id=:id")
    void setDislike(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("select w.id, w.name from Wish w where w.likes = max(w.likes)")
    Wish getMaxLikes();
}
