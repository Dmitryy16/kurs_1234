package ru.mycompany.restapinews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mycompany.restapinews.model.Players;
import ru.mycompany.restapinews.model.Teams;
import ru.mycompany.restapinews.repository.PlayersRepository;
import ru.mycompany.restapinews.repository.TeamsRepository;

import java.util.List;

@Controller
public class PlayersController {

    private final PlayersRepository playersRepository;
    private final TeamsRepository teamsRepository;

    public PlayersController(PlayersRepository playersRepository, TeamsRepository teamsRepository) {
        this.playersRepository = playersRepository;
        this.teamsRepository = teamsRepository;
    }

    @ModelAttribute("teams")
    public List<Teams> populateTeams() {
        return teamsRepository.findAll();
    }

    @GetMapping("/players")
    public String players(Model model) {
        model.addAttribute("playerList", playersRepository.findAll());
        return "players";
    }

    @GetMapping("/players/create")
    public String showAddPlayerForm(Model model) {
        model.addAttribute("player", new Players());
        model.addAttribute("teams", teamsRepository.findAll());
        return "createPlayers";
    }

    @PostMapping("/players/create")
    public String addPlayer(@ModelAttribute Players players) {
        playersRepository.save(players);
        return "redirect:/players";
    }

    @GetMapping("/players/delete")
    public String showDeletePlayerForm(Model model) {
        model.addAttribute("playerList", playersRepository.findAll());
        return "deletePlayer";
    }

    @PostMapping("/players/delete")
    public String deletePlayer(@RequestParam Integer playerId) {
        playersRepository.deleteById(playerId);
        return "redirect:/players";
    }

    @GetMapping("/players/update")
    public String showUpdatePlayerForm(Model model) {
        model.addAttribute("playerList", playersRepository.findAll());
        return "selectUpdatePlayerID";
    }

    @GetMapping("/players/update/{id}")
    public String showUpdatePlayerForm(@PathVariable("id") Integer id, Model model) {
        Players player = playersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));
        model.addAttribute("player", player);
        model.addAttribute("teams", teamsRepository.findAll());
        return "updatePlayer";
    }

    @PostMapping("/players/update/{id}")
    public String updatePlayer(@PathVariable("id") Integer id, @ModelAttribute Players player) {
        player.setId(id);
        playersRepository.save(player);
        return "redirect:/players";
    }
}