package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.GearDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.mappers.Mapper;
import com.cycling.stats.repositories.GearRepository;
import com.cycling.stats.repositories.TeamRepository;
import com.cycling.stats.services.GearService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GearServiceImpl implements GearService {

    private final GearRepository gearRepository;
    private final TeamRepository teamRepository;
    private final Mapper<Gear, GearDto> mapper;

    @Override
    public List<GearDto> findAll() {
        return gearRepository
                .findAllNotDeleted()
                .stream()
                .map(mapper::mapFrom)
                .toList();
    }

    @Override
    public Optional<GearDto> findById(Long id) {
        return gearRepository
                .findById(id)
                .filter(gear -> !gear.getDeleted())
                .map(mapper::mapFrom);
    }

    @Override
    public GearDto create(GearDto gearDto) {
        Gear gear = mapper.mapTo(gearDto);
        return mapper.mapFrom(gearRepository.save(gear));
    }

    @Override
    public List<GearDto> createList(List<GearDto> gearDtos) {
        List<Gear> gears = gearDtos
                .stream()
                .map(mapper::mapTo)
                .toList();
        return gearRepository.saveAll(gears).stream().map(mapper::mapFrom).toList();
    }

    @Override
    public Optional<GearDto> update(Long id, GearDto gearDto) {
        gearDto.setId(id);
        if (!gearRepository.existsById(id)) {
            return Optional.empty();
        }
        Gear gear = mapper.mapTo(gearDto);

//        Team team = teamRepository
//                .findById(10L)
//                .orElse(null);
//        if (team != null) {
//            team.setGear(gear);
//        }
        gear.setTeam(null);

        return Optional.ofNullable(mapper.mapFrom(gearRepository.save(gear)));
    }

    @Override
    public Optional<GearDto> partialUpdate(Long id, GearDto gearDto) {
        gearDto.setId(id);
        if (!gearRepository.existsById(id)) {
            return Optional.empty();
        }

        return gearRepository
                .findById(id)
                .map(gear -> {
                    Optional.ofNullable(gearDto.getBike()).ifPresent(gear::setBike);
                    Optional.ofNullable(gearDto.getGroupset()).ifPresent(gear::setGroupset);
                    Optional.ofNullable(gearDto.getWheel()).ifPresent(gear::setWheel);
                    Optional.ofNullable(gearDto.getSaddle()).ifPresent(gear::setSaddle);
                    Optional.ofNullable(gearDto.getTyres()).ifPresent(gear::setTyres);
                    Optional.ofNullable(gearDto.getPedals()).ifPresent(gear::setPedals);
                    Optional.ofNullable(gearDto.getPowermeters()).ifPresent(gear::setPowermeters);
                    Optional.ofNullable(gearDto.getSunglasses()).ifPresent(gear::setSunglasses);
                    Optional.ofNullable(gearDto.getHelmet()).ifPresent(gear::setHelmet);
                    Optional.ofNullable(gearDto.getShoes()).ifPresent(gear::setShoes);
                    Optional.ofNullable(gearDto.getBartape()).ifPresent(gear::setBartape);
                    Optional.ofNullable(gearDto.getKit()).ifPresent(gear::setKit);
                    Optional.ofNullable(gearDto.getSportNutration()).ifPresent(gear::setSportNutration);
                    Optional.ofNullable(gearDto.getCyclingComputer()).ifPresent(gear::setCyclingComputer);
                    Optional.ofNullable(gearDto.getHomeTrainer()).ifPresent(gear::setHomeTrainer);
                    gear.setModifiedDate(LocalDateTime.now());
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
    public Optional<GearDto> softDelete(Long id) {
        if (!gearRepository.existsById(id)) {
            return Optional.empty();
        }
        gearRepository.softDelete(id);
        return gearRepository
                .findById(id)
                .map(mapper::mapFrom);
    }
}