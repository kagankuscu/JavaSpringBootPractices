package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.teamContractDtos.AddTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.UpdateTeamContractDto;

import java.util.List;

public interface TeamContractService {
    List<GetTeamContractDto> findAll();

    GetTeamContractDto findById(Long id);

    GetTeamContractDto create(AddTeamContractDto addTeamContractDto);

    List<GetTeamContractDto> createList(List<AddTeamContractDto> addTeamContractDtos);

    GetTeamContractDto update(Long id, UpdateTeamContractDto updateTeamContractDto);

    GetTeamContractDto partialUpdate(Long id, UpdateTeamContractDto updateTeamContractDto);

    void delete(Long id);

    GetTeamContractDto softDelete(Long id);
}
