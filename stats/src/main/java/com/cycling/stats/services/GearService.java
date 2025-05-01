package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GearDto;

import java.util.List;
import java.util.Optional;

public interface GearService {

    List<GearDto> findAll();

    Optional<GearDto> findById(Long id);

    GearDto create(AddGearDto gearDto);

    List<GearDto> createList(List<AddGearDto> gearsDto);

    Optional<GearDto> update(Long id, GearDto gearDto);

    Optional<GearDto> partialUpdate(Long id, GearDto gearDto);

    boolean delete(Long id);

    Optional<GearDto> softDelete(Long id);
}
