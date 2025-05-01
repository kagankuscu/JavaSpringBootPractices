package com.cycling.stats.domain.dtos.teamDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTeamDto {
    private Long id;
    private String name;
    private String code;
    private String manager;
    private String nationality;
    private String jerseyImg;
    private Long yearFounded;
}
