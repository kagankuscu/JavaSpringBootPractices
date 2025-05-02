package com.cycling.stats.mappers.impl.TeamContract;

import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.entities.TeamContract;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetTeamContractMapperImpl implements Mapper<TeamContract, GetTeamContractDto> {

    private final ModelMapper modelMapper;

    @Override
    public TeamContract mapTo(GetTeamContractDto getTeamContractDto) {
        return modelMapper.map(getTeamContractDto, TeamContract.class);
    }

    @Override
    public GetTeamContractDto mapFrom(TeamContract teamContract) {
        return modelMapper.map(teamContract, GetTeamContractDto.class);
    }
}
