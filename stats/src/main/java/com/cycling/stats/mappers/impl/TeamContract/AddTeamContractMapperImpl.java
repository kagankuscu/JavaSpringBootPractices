package com.cycling.stats.mappers.impl.TeamContract;

import com.cycling.stats.domain.dtos.teamContractDtos.AddTeamContractDto;
import com.cycling.stats.domain.entities.TeamContract;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddTeamContractMapperImpl implements Mapper<TeamContract, AddTeamContractDto> {

    private ModelMapper modelMapper;

    @Override
    public TeamContract mapTo(AddTeamContractDto addTeamContractDto) {
        return modelMapper.map(addTeamContractDto, TeamContract.class);
    }

    @Override
    public AddTeamContractDto mapFrom(TeamContract teamContract) {
        return modelMapper.map(teamContract, AddTeamContractDto.class);
    }
}
