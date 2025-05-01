package com.cycling.stats.mappers.impl.TeamMapper;

import com.cycling.stats.domain.dtos.teamDtos.UpdateTeamDto;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateTeamMapperImpl implements Mapper<Team, UpdateTeamDto> {

    private ModelMapper modelMapper;

    @Override
    public UpdateTeamDto mapFrom(Team team) {
        return modelMapper.map(team, UpdateTeamDto.class);
    }

    @Override
    public Team mapTo(UpdateTeamDto updateTeamDto) {
        return modelMapper.map(updateTeamDto, Team.class);
    }
}