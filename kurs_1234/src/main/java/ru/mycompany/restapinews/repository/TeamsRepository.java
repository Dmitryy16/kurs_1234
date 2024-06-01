package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mycompany.restapinews.model.Players;
import ru.mycompany.restapinews.model.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer> {
    void deleteByPlayers(Players player);

    Teams findByTeamsName(String teamName);
}