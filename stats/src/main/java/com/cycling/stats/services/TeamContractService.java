package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.teamContractDtos.AddTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.UpdateTeamContractDto;

import java.util.List;
import java.util.Optional;

public interface TeamContractService {
    List<GetTeamContractDto> findAll();

    Optional<GetTeamContractDto> findById(Long id);

    GetTeamContractDto create(AddTeamContractDto addTeamContractDto);

    List<GetTeamContractDto> createList(List<AddTeamContractDto> addTeamContractDtos);

    Optional<GetTeamContractDto> update(Long id, UpdateTeamContractDto updateTeamContractDto);

    Optional<GetTeamContractDto> partialUpdate(Long id, UpdateTeamContractDto updateTeamContractDto);

    boolean delete(Long id);

    Optional<GetTeamContractDto> softDelete(Long id);
}
