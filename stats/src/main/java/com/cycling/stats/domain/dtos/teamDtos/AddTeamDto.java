package com.cycling.stats.domain.dtos.teamDtos;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTeamDto {
    private String name;
    private String code;
    private String manager;
    private String nationality;
    private String jerseyImg;
    private Long yearFounded;
}
