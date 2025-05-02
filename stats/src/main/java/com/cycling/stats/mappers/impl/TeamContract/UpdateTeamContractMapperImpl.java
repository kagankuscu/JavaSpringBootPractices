package com.cycling.stats.mappers.impl.TeamContract;

import com.cycling.stats.domain.dtos.teamContractDtos.UpdateTeamContractDto;
import com.cycling.stats.domain.entities.TeamContract;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateTeamContractMapperImpl implements Mapper<TeamContract, UpdateTeamContractDto> {

    private ModelMapper modelMapper;

    @Override
    public TeamContract mapTo(UpdateTeamContractDto updateTeamContractDto) {
        return modelMapper.map(updateTeamContractDto, TeamContract.class);
    }

    @Override
    public UpdateTeamContractDto mapFrom(TeamContract teamContract) {
        return modelMapper.map(teamContract, UpdateTeamContractDto.class);
    }
}
