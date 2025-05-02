package com.cycling.stats.mappers.impl.GearMapper;

import com.cycling.stats.domain.dtos.gearDtos.GetGearDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetGearMapperImpl implements Mapper<Gear, GetGearDto> {

    private final ModelMapper modelMapper;

    @Override
    public Gear mapTo(GetGearDto getGearDto) {
        return modelMapper.map(getGearDto, Gear.class);
    }

    @Override
    public GetGearDto mapFrom(Gear gear) {
        return modelMapper.map(gear, GetGearDto.class);
    }
}
