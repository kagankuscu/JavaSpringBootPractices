package com.cycling.stats.domain.dtos.riderDtos;

import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRiderDto {
    private Long id;
    private String fullName;
    private Date birthdate;
    private String nationality;
    private Long weight;
    private Long height;
    private Long teamId;
}
