package com.cycling.stats.domain.dtos.teamContractDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTeamContractDto {
    private Long id;
    private String name;
    private Long year;

    private List<Long> riderIds;
}
