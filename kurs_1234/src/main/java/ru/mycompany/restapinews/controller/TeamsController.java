package ru.mycompany.restapinews.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mycompany.restapinews.model.Players;
import ru.mycompany.restapinews.model.Teams;
import ru.mycompany.restapinews.repository.TeamsRepository;

import java.util.Optional;
import java.util.Set;

@Transactional
@Controller
@RequiredArgsConstructor
public class TeamsController {

    @Autowired
    private TeamsRepository teamsRepository;

    @GetMapping("/teams")
    public String teams(Model model) {
        model.addAttribute("teamsList", teamsRepository.findAll());
        return "Teams";
    }

    @GetMapping("/teams/create")
    public String createTeams(Model model) {
        model.addAttribute("teams", new Teams());
        return "createTeams";
    }

    @PostMapping("/teams/create")
    public String createTeams(@ModelAttribute Teams teams) {
        teamsRepository.save(teams);
        return "redirect:/teams";
    }

    @GetMapping("/teams/delete")
    public String showDeleteTeamForm(Model model) {
        model.addAttribute("teamsList", teamsRepository.findAll());
        return "deleteTeams";
    }

    @PostMapping("/teams/delete")
    public String deleteTeam(@RequestParam Integer teamsId) {
        teamsRepository.deleteById(teamsId);
        return "redirect:/teams";
    }

    @GetMapping("/teams/average-height")
    public String showAverageHeightForm(Model model) {
        model.addAttribute("teams", teamsRepository.findAll());
        return "averageHeightForm";
    }

    @PostMapping("/teams/average-height")
    public String calculateAverageHeight(@RequestParam String teamName, Model model) {
        Optional<Teams> teamOptional = Optional.ofNullable(teamsRepository.findByTeamsName(teamName));
        if (teamOptional.isPresent()) {
            Teams team = teamOptional.get();
            Set<Players> players = team.getPlayers();
            int totalHeight = 0;
            int count = 0;
            for (Players player : players) {
                if (player.getHeight() != null) {
                    totalHeight += player.getHeight();
                    count++;
                }
            }
            double averageHeight = (count > 0) ? (double) totalHeight / count : 0;

            model.addAttribute("teamName", team.getTeamsName());
            model.addAttribute("averageHeight", averageHeight);
        } else {
            model.addAttribute("message", "Team not found");
        }

        return "averageHeightResult";
    }




}