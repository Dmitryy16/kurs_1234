package ru.mycompany.restapinews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TeamsName")
    private String teamsName;

    @OneToMany(mappedBy = "team")
    private Set<Players> players;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Matches> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Matches> awayMatches;
}