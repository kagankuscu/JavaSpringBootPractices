package com.cycling.stats.mappers.impl.GearMapper;

import com.cycling.stats.domain.dtos.gearDtos.GearDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GearMapperImpl implements Mapper<Gear, GearDto> {

    private final ModelMapper modelMapper;

    @Override
    public Gear mapTo(GearDto gearDto) {
        return modelMapper.map(gearDto, Gear.class);
    }

    @Override
    public GearDto mapFrom(Gear gear) {
        return modelMapper.map(gear, GearDto.class);
    }
}
