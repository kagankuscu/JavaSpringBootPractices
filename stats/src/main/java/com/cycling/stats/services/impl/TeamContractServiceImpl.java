package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.TeamContractDto;
import com.cycling.stats.domain.entities.TeamContract;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.TeamContractRepository;
import com.cycling.stats.services.TeamContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TeamContractServiceImpl implements TeamContractService {

    private final TeamContractRepository teamContractRepository;
    private final Mapper<TeamContract, TeamContractDto> mapper;

    @Override
    public List<TeamContractDto> findAll() {
        return teamContractRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<TeamContractDto> findById(Long id) {
        return teamContractRepository
                .findById(id)
                .filter(teamContract -> !teamContract.getDeleted())
                .map(mapper::mapFrom);
    }

    @Override
    public TeamContractDto create(TeamContractDto teamContractDto) {
        TeamContract teamContract = mapper.mapTo(teamContractDto);
        return mapper.mapFrom(teamContractRepository.save(teamContract));
    }

    @Override
    public List<TeamContractDto> createList(List<TeamContractDto> teamContractDtos) {
        List<TeamContract> teamContracts = teamContractDtos
                .stream()
                .map(mapper::mapTo)
                .toList();
        return teamContractRepository
                .saveAll(teamContracts)
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<TeamContractDto> update(Long id, TeamContractDto teamContractDto) {
        teamContractDto.setId(id);
        if (!teamContractRepository.existsById(id)) {
            return Optional.empty();
        }
        TeamContract teamContract = mapper.mapTo(teamContractDto);

        return Optional.ofNullable(mapper.mapFrom(teamContractRepository.save(teamContract)));
    }

    @Override
    public Optional<TeamContractDto> partialUpdate(Long id, TeamContractDto teamContractDto) {
        teamContractDto.setId(id);
        if (!teamContractRepository.existsById(id)) {
            return Optional.empty();
        }

        return teamContractRepository
                .findById(id)
                .map(tc -> {
                    Optional.ofNullable(teamContractDto.getName()).ifPresent(tc::setName);
                    Optional.ofNullable(teamContractDto.getYear()).ifPresent(tc::setYear);
                    tc.setModifiedDate(LocalDateTime.now());
                    return mapper.mapFrom(teamContractRepository.save(tc));
                });
    }

    @Override
    public boolean delete(Long id) {
        if (!teamContractRepository.existsById(id)) {
            return false;
        }

        teamContractRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<TeamContractDto> softDelete(Long id) {
        if (!teamContractRepository.existsById(id)) {
            return Optional.empty();
        }
        teamContractRepository.softDelete(id);
        return teamContractRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}
