package ru.mycompany.restapinews.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mycompany.restapinews.model.Players;

import java.util.List;

public interface PlayersRepository extends CrudRepository<Players, Integer> {
    List<Players> findByTeamId(Integer teamId);
}
