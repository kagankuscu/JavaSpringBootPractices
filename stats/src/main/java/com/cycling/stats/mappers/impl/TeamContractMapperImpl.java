package com.cycling.stats.mappers.impl;

import com.cycling.stats.domain.dtos.teamContractDtos.TeamContractDto;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.domain.entities.TeamContract;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeamContractMapperImpl implements Mapper<TeamContract, TeamContractDto> {

    private final ModelMapper modelMapper;

    @Override
    public TeamContract mapTo(TeamContractDto teamContractDto) {
        return modelMapper.map(teamContractDto, TeamContract.class);
    }

    @Override
    public TeamContractDto mapFrom(TeamContract teamContract) {
        return modelMapper.map(teamContract, TeamContractDto.class);
    }
}
