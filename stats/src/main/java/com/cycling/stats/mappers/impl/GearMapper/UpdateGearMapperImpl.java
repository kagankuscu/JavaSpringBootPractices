package com.cycling.stats.mappers.impl.GearMapper;

import com.cycling.stats.domain.dtos.gearDtos.UpdateGearDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateGearMapperImpl implements Mapper<Gear, UpdateGearDto> {

    private ModelMapper modelMapper;

    @Override
    public Gear mapTo(UpdateGearDto updateGearDto) {
        return modelMapper.map(updateGearDto, Gear.class);
    }

    @Override
    public UpdateGearDto mapFrom(Gear gear) {
        return modelMapper.map(gear, UpdateGearDto.class);
    }
}
