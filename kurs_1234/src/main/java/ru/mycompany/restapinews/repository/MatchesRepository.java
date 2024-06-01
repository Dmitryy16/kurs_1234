package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mycompany.restapinews.model.Matches;

import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer> {
    void deleteById(Long id);
}