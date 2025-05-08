package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.exceptions.UpdateIdNotEqualGivenException;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.RiderRepository;
import com.cycling.stats.services.RiderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final RiderRepository riderRepository;
    private final Mapper<Rider, GetRiderDto> mapper;
    private final Mapper<Rider, AddRiderDto> addMapper;
    private final Mapper<Rider, UpdateRiderDto> updateMapper;

    @Override
    public List<GetRiderDto> findAll() {
        return riderRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public GetRiderDto findById(Long id) {
        return riderRepository
                .findById(id)
                .filter(rider -> !rider.getDeleted())
                .map(mapper::mapFrom)
                .orElseThrow(() -> new ResourceNotFoundException("rider", id));
    }

    @Override
    public GetRiderDto create(AddRiderDto riderDto) {
        Rider rider = addMapper.mapTo(riderDto);
        if (riderDto.getTeamId() != null) {
            Team team = Team.builder()
                    .id(riderDto.getTeamId())
                    .build();
            rider.setTeam(team);
        }
        return mapper.mapFrom(riderRepository.save(rider));
    }

    @Override
    public List<GetRiderDto> createList(List<AddRiderDto> riderDtos) {
        List<Rider> riders = riderDtos
                .stream()
                .map(r -> {
                    Rider rider = addMapper.mapTo(r);
                    if (r.getTeamId() != null) {
                        Team team = Team.builder()
                                .id(r.getTeamId())
                                .build();
                        rider.setTeam(team);
                    }
                    return rider;
                })
                .toList();
        return riderRepository.saveAll(riders).stream().map(mapper::mapFrom).toList();
    }

    @Override
    public GetRiderDto update(Long id, UpdateRiderDto updateRiderDto) {
        if (!id.equals(updateRiderDto.getId()))
            throw new UpdateIdNotEqualGivenException(id, updateRiderDto.getId());

        riderRepository
                .findById(updateRiderDto.getId())
                .filter(r -> !r.getDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("rider", id));

        Rider rider = updateMapper.mapTo(updateRiderDto);
        if (updateRiderDto.getTeamId() != null) {
            Team team = Team
                    .builder()
                    .id(updateRiderDto.getTeamId())
                    .build();
            rider.setTeam(team);
        }

        return mapper.mapFrom(riderRepository.save(rider));
    }

    @Override
    public GetRiderDto partialUpdate(Long id, UpdateRiderDto updateRiderDto) {
        if (!id.equals(updateRiderDto.getId()))
            throw new UpdateIdNotEqualGivenException(id, updateRiderDto.getId());

        return riderRepository
                .findById(id)
                .map(rider -> {
                    Optional.ofNullable(updateRiderDto.getFullName()).ifPresent(rider::setFullName);
                    Optional.ofNullable(updateRiderDto.getBirthdate()).ifPresent(rider::setBirthdate);
                    Optional.ofNullable(updateRiderDto.getNationality()).ifPresent(rider::setNationality);
                    Optional.ofNullable(updateRiderDto.getWeight()).ifPresent(rider::setWeight);
                    Optional.ofNullable(updateRiderDto.getHeight()).ifPresent(rider::setHeight);
                    Optional.ofNullable(updateRiderDto.getTeamId()).ifPresent(teamId -> {
                       Team team = Team.builder()
                               .id(teamId)
                               .build();
                       rider.setTeam(team);
                    });
                    return mapper.mapFrom(riderRepository.save(rider));
                }).orElseThrow(() -> new ResourceNotFoundException("rider", id));
    }

    @Override
    public void delete(Long id) {
        if (!riderRepository.existsById(id)) {
            throw new ResourceNotFoundException("rider", id);
        }

        riderRepository.deleteById(id);
    }

    @Override
    public GetRiderDto softDelete(Long id) {
        riderRepository.softDelete(id);
        return riderRepository
                .findById(id)
                .map(mapper::mapFrom)
                .orElseThrow(() -> new ResourceNotFoundException("rider", id));
    }
}
