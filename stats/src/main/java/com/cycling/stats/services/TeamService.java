package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.UpdateTeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<GetTeamDto> findAll();

    Optional<GetTeamDto> findById(Long id);

    GetTeamDto create(AddTeamDto addTeamDto);

    List<GetTeamDto> createList(List<AddTeamDto> addTeamDtos);

    Optional<GetTeamDto> update(Long id, UpdateTeamDto updateTeamDto);

    Optional<GetTeamDto> partialUpdate(Long id, UpdateTeamDto updateTeamDto);

    boolean delete(Long id);

    Optional<GetTeamDto> softDelete(Long id);
}
