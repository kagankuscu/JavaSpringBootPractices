package com.cycling.stats.mappers.impl.TeamMapper;

import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddTeamMapperImpl implements Mapper<Team, AddTeamDto> {

    private ModelMapper modelMapper;

    @Override
    public Team mapTo(AddTeamDto addTeamDto) {
        return modelMapper.map(addTeamDto, Team.class);
    }

    @Override
    public AddTeamDto mapFrom(Team team) {
        return modelMapper.map(team, AddTeamDto.class);
    }
}
