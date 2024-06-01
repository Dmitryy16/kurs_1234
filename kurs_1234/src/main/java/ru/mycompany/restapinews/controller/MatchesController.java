package ru.mycompany.restapinews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mycompany.restapinews.model.Matches;
import ru.mycompany.restapinews.repository.MatchesRepository;
import ru.mycompany.restapinews.repository.TeamsRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class MatchesController {
    private final MatchesRepository matchesRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public MatchesController(MatchesRepository matchesRepository, TeamsRepository teamsRepository) {
        this.matchesRepository = matchesRepository;
        this.teamsRepository = teamsRepository;
    }

    @GetMapping("/matches")
    public String matches(Model model) {
        model.addAttribute("matchesList", matchesRepository.findAll());
        return "Matches";
    }

    @GetMapping("/matches/create")
    public String createMatch(Model model) {
        model.addAttribute("match", new Matches());
        model.addAttribute("teams", teamsRepository.findAll());
        return "createMatches";
    }

    @PostMapping("/matches/create")
    public String createMatch(@ModelAttribute Matches match) {
        matchesRepository.save(match);
        return "redirect:/matches";
    }

    @GetMapping("/matches/delete")
    public String showDeleteMatchForm(Model model) {
        model.addAttribute("matchesList", matchesRepository.findAll());
        return "deleteMatches";
    }

    @PostMapping("/matches/delete")
    public String deleteMatch(@RequestParam Integer matchId) {
        matchesRepository.deleteById(matchId);
        return "redirect:/matches";
    }

    @GetMapping("/matches/update")
    public String selectUpdateMatchID(Model model) {
        model.addAttribute("matchesList", matchesRepository.findAll());
        return "selectUpdateMatchID";
    }

    @GetMapping("/matches/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Matches> optionalMatch = matchesRepository.findById(id);
        if (optionalMatch.isPresent()) {
            Matches match = optionalMatch.get();
            model.addAttribute("match", match);
            model.addAttribute("teams", teamsRepository.findAll());
            return "updateMatches";
        } else {
            model.addAttribute("error", "Invalid match Id:" + id);
            return "error";
        }
    }


    @PostMapping("/matches/update/{id}")
    public String updateMatch(@PathVariable("id") Integer id, @ModelAttribute Matches match) {
        match.setId(id);
        matchesRepository.save(match);
        return "redirect:/matches";
    }

}
