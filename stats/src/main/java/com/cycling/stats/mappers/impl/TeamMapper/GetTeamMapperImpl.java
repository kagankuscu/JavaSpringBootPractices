package com.cycling.stats.mappers.impl.TeamMapper;

import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetTeamMapperImpl implements Mapper<Team, GetTeamDto> {

    private final ModelMapper modelMapper;

    @Override
    public Team mapTo(GetTeamDto getTeamDto) {
        return modelMapper.map(getTeamDto, Team.class);
     }

    @Override
    public GetTeamDto mapFrom(Team team) {
        return modelMapper.map(team, GetTeamDto.class);
    }
}
