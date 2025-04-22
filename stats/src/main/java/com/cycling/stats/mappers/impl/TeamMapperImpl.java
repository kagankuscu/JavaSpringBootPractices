package com.cycling.stats.mappers.impl;

import com.cycling.stats.domain.dtos.TeamDto;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeamMapperImpl implements Mapper<Team, TeamDto> {

    private final ModelMapper modelMapper;

    @Override
    public Team mapTo(TeamDto teamDto) {
        return modelMapper.map(teamDto, Team.class);
    }

    @Override
    public TeamDto mapFrom(Team team) {
        return modelMapper.map(team, TeamDto.class);
    }
}
