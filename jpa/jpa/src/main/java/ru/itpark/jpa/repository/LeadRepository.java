package ru.itpark.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.jpa.entity.Lead;

@Repository
public interface LeadRepository extends JpaRepository
<Lead, Integer> {
}
