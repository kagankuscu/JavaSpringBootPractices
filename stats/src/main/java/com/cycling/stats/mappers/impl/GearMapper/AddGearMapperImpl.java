package com.cycling.stats.mappers.impl.GearMapper;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddGearMapperImpl implements Mapper<Gear, AddGearDto> {

    private ModelMapper modelMapper;

    @Override
    public Gear mapTo(AddGearDto addGearDto) {
        return modelMapper.map(addGearDto, Gear.class);
    }

    @Override
    public AddGearDto mapFrom(Gear gear) {
        return modelMapper.map(gear, AddGearDto.class);
    }
}
