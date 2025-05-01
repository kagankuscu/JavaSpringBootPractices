package com.cycling.stats.domain.dtos.teamDtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetTeamDto {
    private Long id;
    private String name;
    private String code;
    private String manager;
    private String nationality;
    private String jerseyImg;
    private Long yearFounded;
}
