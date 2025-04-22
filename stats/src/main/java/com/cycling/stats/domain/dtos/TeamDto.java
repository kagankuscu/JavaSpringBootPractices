package com.cycling.stats.domain.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TeamDto {
    private Long id;
    private String name;
    private String code;
    private String manager;
    private String nationality;
    private String jerseyImg;
    private Long yearFounded;
    private List<RiderDto> riders;
    private GearDto gear;
}
