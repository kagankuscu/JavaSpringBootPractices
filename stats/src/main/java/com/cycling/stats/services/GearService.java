package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.GearDto;

import java.util.List;
import java.util.Optional;

public interface GearService {

    List<GearDto> findAll();

    Optional<GearDto> findById(Long id);

    GearDto create(GearDto gearDto);

    List<GearDto> createList(List<GearDto> gearsDto);

    Optional<GearDto> update(Long id, GearDto gearDto);

    Optional<GearDto> partialUpdate(Long id, GearDto gearDto);

    boolean delete(Long id);

    Optional<GearDto> softDelete(Long id);
}
