package ru.mycompany.restapinews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MatchID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "HomeTeamID")
    private Teams homeTeam;

    @ManyToOne
    @JoinColumn(name = "AwayTeamID")
    private Teams awayTeam;

    @Column(name = "HomeTeamScore")
    private Integer homeTeamScore;

    @Column(name = "AwayTeamScore")
    private Integer awayTeamScore;

    @Column(name = "Assists")
    private Integer assists;

    @Column(name = "Rebounds")
    private Integer rebounds;

    @Column(name = "MatchDate")
    private Date matchDate;
}