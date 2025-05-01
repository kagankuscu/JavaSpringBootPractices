package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.UpdateTeamDto;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.TeamRepository;
import com.cycling.stats.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final Mapper<Team, GetTeamDto> mapper;
    private final Mapper<Team, AddTeamDto> addMapper;
    private final Mapper<Team, UpdateTeamDto> updateMapper;

    @Override
    public List<GetTeamDto> findAll() {
        return teamRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<GetTeamDto> findById(Long id) {
        return teamRepository
                .findById(id)
                .filter(team -> !team.getDeleted())
                .map(mapper::mapFrom);
    }

    @Override
    public GetTeamDto create(AddTeamDto teamDto) {
        Team team = addMapper.mapTo(teamDto);
        return mapper.mapFrom(teamRepository.save(team));
    }

    @Override
    public List<GetTeamDto> createList(List<AddTeamDto> teamsDto) {
        List<Team> teams = teamsDto
                .stream()
                .map(addMapper::mapTo)
                .toList();
        return teamRepository
                .saveAll(teams)
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<GetTeamDto> update(Long id, UpdateTeamDto updateTeamDto) {
        updateTeamDto.setId(id);
        if (!teamRepository.existsById(id)) {
            return Optional.empty();
        }
        Team team = updateMapper.mapTo(updateTeamDto);

        return Optional.ofNullable(mapper.mapFrom(teamRepository.save(team)));
    }

    @Override
    public Optional<GetTeamDto> partialUpdate(Long id, UpdateTeamDto updateTeamDto) {
        updateTeamDto.setId(id);
        if (!teamRepository.existsById(id)) {
            return Optional.empty();
        }
        return teamRepository
                .findById(id)
                .map(team -> {
                    Optional.ofNullable(updateTeamDto.getName()).ifPresent(team::setName);
                    Optional.ofNullable(updateTeamDto.getCode()).ifPresent(team::setCode);
                    Optional.ofNullable(updateTeamDto.getManager()).ifPresent(team::setManager);
                    Optional.ofNullable(updateTeamDto.getNationality()).ifPresent(team::setNationality);
                    Optional.ofNullable(updateTeamDto.getJerseyImg()).ifPresent(team::setJerseyImg);
                    Optional.ofNullable(updateTeamDto.getYearFounded()).ifPresent(team::setYearFounded);
                    team.setModifiedDate(LocalDateTime.now());
                    return mapper.mapFrom(teamRepository.save(team));
                });
    }

    @Override
    public boolean delete(Long id) {
        if (!teamRepository.existsById(id)) {
            return false;
        }

        teamRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<GetTeamDto> softDelete(Long id) {
        if (!teamRepository.existsById(id)) {
            return Optional.empty();
        }

        teamRepository.softDelete(id);
        return teamRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}
