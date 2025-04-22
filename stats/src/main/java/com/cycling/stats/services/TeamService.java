package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.TeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<TeamDto> findAll();

    Optional<TeamDto> findById(Long id);

    TeamDto create(TeamDto teamDto);

    List<TeamDto> createList(List<TeamDto> teamsDto);

    Optional<TeamDto> update(Long id, TeamDto teamDto);

    Optional<TeamDto> partialUpdate(Long id, TeamDto teamDto);

    boolean delete(Long id);

    Optional<TeamDto> softDelete(Long id);
}
