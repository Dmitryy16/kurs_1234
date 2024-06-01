package ru.mycompany.restapinews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlayerID")
    private Integer id;

    @Column(name = "PlayerName", nullable = false)
    private String playerName;

    @ManyToOne
    @JoinColumn(name = "TeamID")
    private Teams team;

    @Column(name = "Position")
    private String position;

    @Column(name = "Height")
    private Integer height;

    @Column(name = "Weight")
    private Integer weight;
}