package com.cycling.stats.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "riders")
public class Rider extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Date birthdate;
    private String nationality;
    private Long weight;
    private Long height;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToMany(mappedBy = "riders", cascade = CascadeType.ALL)
    private List<TeamContract> teamContracts;
}
