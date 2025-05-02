package com.cycling.stats.domain.dtos.riderDtos;

import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRiderDto {
    private Long id;
    private String fullName;
    private Date birthdate;
    private String nationality;
    private Long weight;
    private Long height;
    private GetTeamDto team;
    private List<GetTeamContractDto> teamContracts;
}
