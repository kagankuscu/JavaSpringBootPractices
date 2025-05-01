package com.cycling.stats.domain.dtos.gearDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddGearDto {
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
    private Long teamId;
}
