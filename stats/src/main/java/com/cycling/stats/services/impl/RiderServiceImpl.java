package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.RiderRepository;
import com.cycling.stats.services.RiderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Optional<GetRiderDto> findById(Long id) {
        return riderRepository
                .findById(id)
                .filter(rider -> !rider.getDeleted())
                .map(mapper::mapFrom);
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
    public Optional<GetRiderDto> update(Long id, UpdateRiderDto updateRiderDto) {
        updateRiderDto.setId(id);
        Optional<Rider> realRider = riderRepository
                .findById(updateRiderDto.getId())
                .filter(r -> !r.getDeleted());
        if (realRider.isEmpty()) {
            return Optional.empty();
        }

        Rider rider = updateMapper.mapTo(updateRiderDto);
        if (updateRiderDto.getTeamId() != null) {
            Team team = Team
                    .builder()
                    .id(updateRiderDto.getTeamId())
                    .build();
            rider.setTeam(team);
        }

        return Optional.ofNullable(mapper.mapFrom(riderRepository.save(rider)));
    }

    @Override
    public Optional<GetRiderDto> partialUpdate(Long id, UpdateRiderDto updateRiderDto) {
        updateRiderDto.setId(id);
        Optional<Rider> realRider = riderRepository
                .findById(updateRiderDto.getId())
                .filter(r -> !r.getDeleted());
        if (realRider.isEmpty()) {
            return Optional.empty();
        }
        if (updateRiderDto.getTeamId() != null) {
            Team team = Team
                    .builder()
                    .id(updateRiderDto.getTeamId())
                    .build();
            realRider
                    .get()
                    .setTeam(team);
        }
        return riderRepository
                .findById(id)
                .map(rider -> {
                    Optional.ofNullable(updateRiderDto.getFullName()).ifPresent(rider::setFullName);
                    Optional.ofNullable(updateRiderDto.getBirthdate()).ifPresent(rider::setBirthdate);
                    Optional.ofNullable(updateRiderDto.getNationality()).ifPresent(rider::setNationality);
                    Optional.ofNullable(updateRiderDto.getWeight()).ifPresent(rider::setWeight);
                    Optional.ofNullable(updateRiderDto.getHeight()).ifPresent(rider::setHeight);
                    Optional.ofNullable(realRider.get().getTeam()).ifPresent(rider::setTeam);
                    return mapper.mapFrom(riderRepository.save(rider));
                });
    }

    @Override
    public boolean delete(Long id) {
        if (!riderRepository.existsById(id)) {
            return false;
        }

        riderRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<GetRiderDto> softDelete(Long id) {
        if (!riderRepository.existsById(id)) {
            return Optional.empty();
        }
        riderRepository.softDelete(id);
        return riderRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}
