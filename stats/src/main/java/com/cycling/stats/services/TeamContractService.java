package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.teamContractDtos.TeamContractDto;

import java.util.List;
import java.util.Optional;

public interface TeamContractService {
    List<TeamContractDto> findAll();

    Optional<TeamContractDto> findById(Long id);

    TeamContractDto create(TeamContractDto teamContractDto);

    List<TeamContractDto> createList(List<TeamContractDto> teamContractDtos);

    Optional<TeamContractDto> update(Long id, TeamContractDto teamContractDto);

    Optional<TeamContractDto> partialUpdate(Long id, TeamContractDto teamContractDto);

    boolean delete(Long id);

    Optional<TeamContractDto> softDelete(Long id);
}
