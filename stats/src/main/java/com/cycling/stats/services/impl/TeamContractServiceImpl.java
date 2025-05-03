package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.teamContractDtos.AddTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.UpdateTeamContractDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.domain.entities.TeamContract;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.exceptions.UpdateIdNotEqualGivenException;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.TeamContractRepository;
import com.cycling.stats.services.TeamContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TeamContractServiceImpl implements TeamContractService {

    private final TeamContractRepository teamContractRepository;
    private final Mapper<TeamContract, GetTeamContractDto> mapper;
    private final Mapper<TeamContract, AddTeamContractDto> addMapper;
    private final Mapper<TeamContract, UpdateTeamContractDto> updateMapper;


    @Override
    public List<GetTeamContractDto> findAll() {
        return teamContractRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public GetTeamContractDto findById(Long id) {
        return teamContractRepository
                .findById(id)
                .filter(teamContract -> !teamContract.getDeleted())
                .map(mapper::mapFrom)
                .orElseThrow(() -> new ResourceNotFoundException("TeamContract Not Found. Id: " + id));
    }

    @Override
    public GetTeamContractDto create(AddTeamContractDto addTeamContractDto) {
        TeamContract teamContract = addMapper.mapTo(addTeamContractDto);
        if (addTeamContractDto.getRiderIds() != null) {
            teamContract.setRiders(setRiders(addTeamContractDto.getRiderIds()));
        }
        return mapper.mapFrom(teamContractRepository.save(teamContract));
    }

    @Override
    public List<GetTeamContractDto> createList(List<AddTeamContractDto> addTeamContractDtos) {
        List<TeamContract> teamContracts = addTeamContractDtos
                .stream()
                .map(tc -> {
                    TeamContract teamContract = addMapper.mapTo(tc);
                    teamContract.setRiders(setRiders(tc.getRiderIds()));
                    return teamContract;
                })
                .toList();
        return teamContractRepository
                .saveAll(teamContracts)
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public GetTeamContractDto update(Long id, UpdateTeamContractDto updateTeamContractDto) {
        if (!id.equals(updateTeamContractDto.getId()))
            throw new UpdateIdNotEqualGivenException("Given id: "
                    + id
                    + " Body id: " + updateTeamContractDto.getId() + " is not equals.");

        teamContractRepository
                .findById(id)
                .filter(tc -> !tc.getDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("TeamContract Not Found. Id: " + id));

        TeamContract teamContract = updateMapper.mapTo(updateTeamContractDto);
        teamContract.setRiders(setRiders(updateTeamContractDto.getRiderIds()));

        return mapper.mapFrom(teamContractRepository.save(teamContract));
    }

    @Override
    public GetTeamContractDto partialUpdate(Long id, UpdateTeamContractDto updateTeamContractDto) {
        if (!id.equals(updateTeamContractDto.getId()))
            throw new UpdateIdNotEqualGivenException("Given id: "
                    + id
                    + " Body id: " + updateTeamContractDto.getId() + " is not equals.");

        return teamContractRepository
                    .findById(id)
                    .map(tc -> {
                        Optional.ofNullable(updateTeamContractDto.getName()).ifPresent(tc::setName);
                        Optional.ofNullable(updateTeamContractDto.getYear()).ifPresent(tc::setYear);
                        Optional.ofNullable(updateTeamContractDto.getRiderIds()).ifPresent(ids -> {
                            tc.setRiders(setRiders(ids));
                        });
                        return mapper.mapFrom(teamContractRepository.save(tc));
                    })
                .orElseThrow(() -> new ResourceNotFoundException("TeamContract Not Found. Id: " + id));
    }

    @Override
    public void delete(Long id) {
        if (!teamContractRepository.existsById(id)) {
            throw new ResourceNotFoundException("TeamContract Not Found. Id: " + id);
        }

        teamContractRepository.deleteById(id);
    }

    @Override
    public GetTeamContractDto softDelete(Long id) {
        teamContractRepository.softDelete(id);
        return teamContractRepository
                .findById(id)
                .map(mapper::mapFrom)
                .orElseThrow(() -> new ResourceNotFoundException("TeamContract Not Found. Id: " + id));
    }

    private List<Rider> setRiders(List<Long> riderIds) {
        List<Rider> riders = new ArrayList<>();
        if (riderIds != null) {
            for (Long ids : riderIds) {
                Rider rider = Rider.builder()
                        .id(ids)
                        .build();
                riders.add(rider);
            }
        }
        return riders;
    }
}
