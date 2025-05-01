package com.cycling.stats.domain.dtos.teamContractDtos;

import com.cycling.stats.domain.dtos.riderDtos.RiderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamContractDto {

    private Long id;
    private String name;
    private Long year;

    private List<RiderDto> riders;
}
