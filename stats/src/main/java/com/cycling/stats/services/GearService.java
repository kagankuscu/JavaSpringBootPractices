package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GetGearDto;
import com.cycling.stats.domain.dtos.gearDtos.UpdateGearDto;
import org.hibernate.sql.Update;

import java.util.List;
import java.util.Optional;

public interface GearService {

    List<GetGearDto> findAll();

    Optional<GetGearDto> findById(Long id);

    GetGearDto create(AddGearDto gearDto);

    List<GetGearDto> createList(List<AddGearDto> gearsDto);

    Optional<GetGearDto> update(Long id, UpdateGearDto updateGearDto);

    Optional<GetGearDto> partialUpdate(Long id, UpdateGearDto updateGearDto);

    boolean delete(Long id);

    Optional<GetGearDto> softDelete(Long id);
}
