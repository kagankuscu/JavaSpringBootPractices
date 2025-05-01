package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.RiderDto;
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
    private final Mapper<Rider, RiderDto> mapper;
    private final Mapper<Rider, AddRiderDto> addMapper;

    @Override
    public List<RiderDto> findAll() {
        return riderRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<RiderDto> findById(Long id) {
        return riderRepository
                .findById(id)
                .filter(rider -> !rider.getDeleted())
                .map(mapper::mapFrom);
    }

    @Override
    public RiderDto create(AddRiderDto riderDto) {
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
    public List<RiderDto> createList(List<AddRiderDto> riderDtos) {
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
    public Optional<RiderDto> update(Long id, RiderDto riderDto) {
        riderDto.setId(id);
        if (!riderRepository.existsById(id)) {
            return Optional.empty();
        }
        Rider rider = mapper.mapTo(riderDto);

        return Optional.ofNullable(mapper.mapFrom(riderRepository.save(rider)));
    }

    @Override
    public Optional<RiderDto> partialUpdate(Long id, RiderDto riderDto) {
        riderDto.setId(id);
        if (!riderRepository.existsById(id)) {
            return Optional.empty();
        }

        return riderRepository
                .findById(id)
                .map(rider -> {
                    Optional.ofNullable(riderDto.getFullName()).ifPresent(rider::setFullName);
                    Optional.ofNullable(riderDto.getBirthdate()).ifPresent(rider::setBirthdate);
                    Optional.ofNullable(riderDto.getNationality()).ifPresent(rider::setNationality);
                    Optional.ofNullable(riderDto.getWeight()).ifPresent(rider::setWeight);
                    Optional.ofNullable(riderDto.getHeight()).ifPresent(rider::setHeight);
                    rider.setModifiedDate(LocalDateTime.now());
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
    public Optional<RiderDto> softDelete(Long id) {
        if (!riderRepository.existsById(id)) {
            return Optional.empty();
        }
        riderRepository.softDelete(id);
        return riderRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}
