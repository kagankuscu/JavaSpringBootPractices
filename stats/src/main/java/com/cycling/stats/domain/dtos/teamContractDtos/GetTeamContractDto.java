package com.cycling.stats.domain.dtos.teamContractDtos;

import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTeamContractDto {

    private Long id;
    private String name;
    private Long year;

//    private List<GetRiderDto> riders;
}
