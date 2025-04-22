package com.cycling.stats.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gears")
public class Gear extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bike;
    private String groupset;
    private String wheel;
    private String saddle;
    private String tyres;
    private String pedals;
    private String powermeters;
    private String sunglasses;
    private String helmet;
    private String shoes;
    private String bartape;
    private String kit;
    private String sportNutration;
    private String cyclingComputer;
    private String homeTrainer;

    @OneToOne(mappedBy = "gear")
    private Team team;
}
