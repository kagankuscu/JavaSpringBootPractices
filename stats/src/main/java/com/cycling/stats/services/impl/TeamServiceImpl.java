package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.TeamDto;
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
    private final Mapper<Team, TeamDto> mapper;

    @Override
    public List<TeamDto> findAll() {
        return teamRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<TeamDto> findById(Long id) {
        return teamRepository
                .findById(id)
                .filter(team -> !team.getDeleted())
                .map(mapper::mapFrom);
    }

    @Override
    public TeamDto create(TeamDto teamDto) {
        Team team = mapper.mapTo(teamDto);
        return mapper.mapFrom(teamRepository.save(team));
    }

    @Override
    public List<TeamDto> createList(List<TeamDto> teamsDto) {
        List<Team> teams = teamsDto
                .stream()
                .map(mapper::mapTo)
                .toList();
        return teamRepository
                .saveAll(teams)
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<TeamDto> update(Long id, TeamDto teamDto) {
        teamDto.setId(id);
        if (!teamRepository.existsById(id)) {
            return Optional.empty();
        }
        Team team = mapper.mapTo(teamDto);

        return Optional.ofNullable(mapper.mapFrom(teamRepository.save(team)));
    }

    @Override
    public Optional<TeamDto> partialUpdate(Long id, TeamDto teamDto) {
        teamDto.setId(id);
        if (!teamRepository.existsById(id)) {
            return Optional.empty();
        }
        return teamRepository
                .findById(id)
                .map(team -> {
                    Optional.ofNullable(teamDto.getName()).ifPresent(team::setName);
                    Optional.ofNullable(teamDto.getCode()).ifPresent(team::setCode);
                    Optional.ofNullable(teamDto.getManager()).ifPresent(team::setManager);
                    Optional.ofNullable(teamDto.getNationality()).ifPresent(team::setNationality);
                    Optional.ofNullable(teamDto.getJerseyImg()).ifPresent(team::setJerseyImg);
                    Optional.ofNullable(teamDto.getYearFounded()).ifPresent(team::setYearFounded);
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
    public Optional<TeamDto> softDelete(Long id) {
        if (!teamRepository.existsById(id)) {
            return Optional.empty();
        }

        teamRepository.softDelete(id);
        return teamRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}
