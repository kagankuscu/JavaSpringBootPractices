package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GetGearDto;
import com.cycling.stats.domain.dtos.gearDtos.UpdateGearDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.GearRepository;
import com.cycling.stats.services.GearService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GearServiceImpl implements GearService {

    private final GearRepository gearRepository;
    private final Mapper<Gear, GetGearDto> mapper;
    private final Mapper<Gear, AddGearDto> addMapper;
    private final Mapper<Gear, UpdateGearDto> updateMapper;

    @Override
    public List<GetGearDto> findAll() {
        return gearRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<GetGearDto> findById(Long id) {
        return gearRepository
                .findById(id)
                .filter(gear -> !gear.getDeleted())
                .map(mapper::mapFrom);
    }

    @Override
    public GetGearDto create(AddGearDto gearDto) {
        Gear gear = addMapper.mapTo(gearDto);
        if (gearDto.getTeamId() != null) {
            Team team = Team
                    .builder()
                    .id(gearDto.getTeamId())
                    .build();
            gear.setTeam(team);
        }
        return mapper.mapFrom(gearRepository.save(gear));
    }

    @Override
    public List<GetGearDto> createList(List<AddGearDto> gearDtos) {
        List<Gear> gears = gearDtos
                .stream()
                .map(g -> {
                    Gear gear = addMapper.mapTo(g);
                    if (g.getTeamId() != null) {
                        Team team = Team
                                .builder()
                                .id(g.getTeamId())
                                .build();
                        gear.setTeam(team);
                    }
                    return gear;
                })
                .toList();
        return gearRepository.saveAll(gears).stream().map(mapper::mapFrom).toList();
    }

    @Override
    public Optional<GetGearDto> update(Long id, UpdateGearDto updateGearDto) {
        updateGearDto.setId(id);
        Optional<Gear> foundNotDeletedGear = gearRepository
                .findById(id)
                .filter(g -> !g.getDeleted());
        if (!gearRepository.existsById(id) || foundNotDeletedGear.isEmpty()) {
            return Optional.empty();
        }
        Gear gear = updateMapper.mapTo(updateGearDto);
        if (updateGearDto.getTeamId() != null) {
            Team team = Team
                    .builder()
                    .id(updateGearDto.getTeamId())
                    .build();
            gear.setTeam(team);
        }
        return Optional.ofNullable(mapper.mapFrom(gearRepository.save(gear)));
    }

    @Override
    public Optional<GetGearDto> partialUpdate(Long id, UpdateGearDto updateGearDto) {
        updateGearDto.setId(id);
        Optional<Gear> foundNotDeletedGear = gearRepository
                .findById(id)
                .filter(g -> !g.getDeleted());
        if (!gearRepository.existsById(id) || foundNotDeletedGear.isEmpty()) {
            return Optional.empty();
        }

        return gearRepository
                .findById(id)
                .map(gear -> {
                    Optional.ofNullable(updateGearDto.getBike()).ifPresent(gear::setBike);
                    Optional.ofNullable(updateGearDto.getGroupset()).ifPresent(gear::setGroupset);
                    Optional.ofNullable(updateGearDto.getWheel()).ifPresent(gear::setWheel);
                    Optional.ofNullable(updateGearDto.getSaddle()).ifPresent(gear::setSaddle);
                    Optional.ofNullable(updateGearDto.getTyres()).ifPresent(gear::setTyres);
                    Optional.ofNullable(updateGearDto.getPedals()).ifPresent(gear::setPedals);
                    Optional.ofNullable(updateGearDto.getPowermeters()).ifPresent(gear::setPowermeters);
                    Optional.ofNullable(updateGearDto.getSunglasses()).ifPresent(gear::setSunglasses);
                    Optional.ofNullable(updateGearDto.getHelmet()).ifPresent(gear::setHelmet);
                    Optional.ofNullable(updateGearDto.getShoes()).ifPresent(gear::setShoes);
                    Optional.ofNullable(updateGearDto.getBartape()).ifPresent(gear::setBartape);
                    Optional.ofNullable(updateGearDto.getKit()).ifPresent(gear::setKit);
                    Optional.ofNullable(updateGearDto.getSportNutration()).ifPresent(gear::setSportNutration);
                    Optional.ofNullable(updateGearDto.getCyclingComputer()).ifPresent(gear::setCyclingComputer);
                    Optional.ofNullable(updateGearDto.getHomeTrainer()).ifPresent(gear::setHomeTrainer);
                    return mapper.mapFrom(gearRepository.save(gear));
                });
    }

    @Override
    public boolean delete(Long id) {
        if (!gearRepository.existsById(id)) {
            return false;
        }

        gearRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<GetGearDto> softDelete(Long id) {
        if (!gearRepository.existsById(id)) {
            return Optional.empty();
        }
        gearRepository.softDelete(id);
        return gearRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}