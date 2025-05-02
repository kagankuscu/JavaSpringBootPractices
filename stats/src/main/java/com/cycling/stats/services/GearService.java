package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GetGearDto;
import com.cycling.stats.domain.dtos.gearDtos.UpdateGearDto;
import org.hibernate.sql.Update;

import java.util.List;
import java.util.Optional;

public interface GearService {

    List<GetGearDto> findAll();

    GetGearDto findById(Long id);

    GetGearDto create(AddGearDto gearDto);

    List<GetGearDto> createList(List<AddGearDto> gearsDto);

    GetGearDto update(Long id, UpdateGearDto updateGearDto);

    GetGearDto partialUpdate(Long id, UpdateGearDto updateGearDto);

    void delete(Long id);

    GetGearDto softDelete(Long id);
}
