package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.UpdateTeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<GetTeamDto> findAll();

    GetTeamDto findById(Long id);

    GetTeamDto create(AddTeamDto addTeamDto);

    List<GetTeamDto> createList(List<AddTeamDto> addTeamDtos);

    GetTeamDto update(Long id, UpdateTeamDto updateTeamDto);

    GetTeamDto partialUpdate(Long id, UpdateTeamDto updateTeamDto);

    void delete(Long id);

    GetTeamDto softDelete(Long id);
}
