package ru.mycompany.restapinews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.mycompany.restapinews.model.Matches;
import ru.mycompany.restapinews.repository.MatchesRepository;
import ru.mycompany.restapinews.repository.PlayersRepository;

@Controller
public class HomeController {

    private final MatchesRepository matchesRepository;
    private final PlayersRepository playersRepository;

    public HomeController(MatchesRepository matchesRepository, PlayersRepository playersRepository) {
        this.matchesRepository = matchesRepository;
        this.playersRepository = playersRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("matchesList", matchesRepository.findAll());
        return "home";
    }

    @GetMapping("/match/{matchId}")
    public String matchDetails(Model model, @PathVariable Integer matchId) {
        Matches match = matchesRepository.findById(matchId).orElse(null);
        if (match != null) {
            model.addAttribute("matchDetails", match);
            model.addAttribute("homeTeamPlayers", playersRepository.findByTeamId(Math.toIntExact(match.getHomeTeam().getId())));
            model.addAttribute("awayTeamPlayers", playersRepository.findByTeamId(Math.toIntExact(match.getAwayTeam().getId())));
        }
        return "matchDetails";
    }
}

