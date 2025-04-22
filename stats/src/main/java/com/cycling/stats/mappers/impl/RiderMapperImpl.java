package com.cycling.stats.mappers.impl;

import com.cycling.stats.domain.dtos.RiderDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RiderMapperImpl implements Mapper<Rider, RiderDto> {

    private final ModelMapper modelMapper;

    @Override
    public Rider mapTo(RiderDto riderDto) {
        return modelMapper.map(riderDto, Rider.class);
    }

    @Override
    public RiderDto mapFrom(Rider rider) {
        return modelMapper.map(rider, RiderDto.class);
    }
}
