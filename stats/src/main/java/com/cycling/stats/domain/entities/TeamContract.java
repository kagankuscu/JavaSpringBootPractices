package com.cycling.stats.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "team_contract")
public class TeamContract extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long year;

    @ManyToMany
    @JoinTable(
        name = "team_contract_rider",
        joinColumns = @JoinColumn(name = "team_contract_id"),
        inverseJoinColumns = @JoinColumn(name = "rider_id"))
    private List<Rider> riders;
}
